package backend.auth.config;

import backend.exceptions.definition.Auth.NoCredentialsException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.PrintWriter;
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private Environment env;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        //LOGGER.error("Responding with unauthorized error. Message - {}", authException.getMessage());
        //authException.printStackTrace();
        System.out.println("Responding with unauthorized error. Message - {}" + authException.getMessage());
        authException.printStackTrace();
        System.out.println(">> Will be requesting credentials");
        out.print(new NoCredentialsException(env));
        out.flush();
        out.close();
    }
}
