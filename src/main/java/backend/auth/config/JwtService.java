package backend.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    public final static HashMap<String, List<String>> revokedTokens = new HashMap<>();
    public final static HashMap<String, String> activeTokens = new HashMap<>();
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static String md5(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(jLoginDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            jLoginDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("role", ((GrantedAuthority) userDetails.getAuthorities().toArray()[0]).getAuthority())

                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*60*24))
                .setHeaderParam("typ", "JWT")
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, jLoginDetails userDetails) {
        final String username = extractUsername(token);
        return isTokenActive(username) && (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && !isRevoked(username, token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private boolean isTokenActive(String username) {
        return activeTokens.containsKey(username);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void addActive(String username, String token) {
        activeTokens.put(username, md5(token));
    }

    public void removeActive(String username) {
        String token_value = activeTokens.remove(username);
        if (token_value != null)
            this.addRevoked(username, token_value);
    }

    public boolean isRevoked(String username, String md5_token) {
        if (revokedTokens.containsKey(username))
            return revokedTokens.get(username).contains(md5(md5_token));
        return false;
    }

    public void addRevoked(String username, String md5_token) {
        if (!revokedTokens.containsKey(username))
            revokedTokens.put(username, new ArrayList<String>());
        revokedTokens.get(username).add(md5_token);
    }
}
