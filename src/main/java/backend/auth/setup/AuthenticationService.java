package backend.auth.setup;


import backend.auth.config.JwtService;
import backend.auth.config.jLoginDetails;
import backend.auth.config.jLoginService;
import lombok.RequiredArgsConstructor;
import openAPI.model.TokenMessage;
import openAPI.model.XLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import openAPI.model.NotOkayMessage;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final jLoginService loginService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${message.LogoutMessage}")
    String messageDeconnexion;

    public TokenMessage authenticate(XLogin request) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUtilisateur(), request.getMotDePasse()));

        jLoginDetails user = (jLoginDetails) loginService.loadUserByUsername(request.getUtilisateur());

        jwtService.removeActive(request.getUtilisateur());
        var jwtToken = jwtService.generateToken(user);
        jwtService.addActive(request.getUtilisateur(), jwtToken);

        return new TokenMessage().token(jwtToken);
    }
    public NotOkayMessage logout() {
        jLoginDetails user = (jLoginDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        SecurityContextHolder.clearContext();
       jwtService.removeActive(user.getUsername());
        return new NotOkayMessage().message(messageDeconnexion);
    }


}
