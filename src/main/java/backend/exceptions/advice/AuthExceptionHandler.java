package backend.exceptions.advice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import openAPI.model.NotOkayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
@Order(1)
public class AuthExceptionHandler {
    @Autowired
    private Environment env;
    @Value("${message.BadCredentialsException}")
    String message;
    // Exception handler for BadCredentialsException
    @ExceptionHandler({BadCredentialsException.class })
    public ResponseEntity<NotOkayMessage> handleBadCredentialsException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new NotOkayMessage().message(message),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    // Exception handler for AccessDeniedException
    @ExceptionHandler({AccessDeniedException.class })
    public ResponseEntity<NotOkayMessage> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new NotOkayMessage().message(env.getProperty("message.AccessDeniedException")),
                new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
    // Exception handler for UsernameNotFoundException
    @ExceptionHandler({UsernameNotFoundException.class })
    public ResponseEntity<NotOkayMessage> handleInsufficientAuthenticationException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new NotOkayMessage().message(env.getProperty("message.UsernameNotFoundException")),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // handle ExpiredJwtException
    @ExceptionHandler({ExpiredJwtException.class })
    public ResponseEntity<NotOkayMessage> handleExpiredJwtException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new NotOkayMessage().message(env.getProperty("message.ExpiredJwtException")),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    // handle
    @ExceptionHandler({MalformedJwtException.class })
    public ResponseEntity<NotOkayMessage> handleMalformedJwtException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new NotOkayMessage().message(env.getProperty("message.MalformedJwtException")),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({SignatureException.class })
    public ResponseEntity<NotOkayMessage> handleSignatureException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new NotOkayMessage().message(env.getProperty("message.SignatureException")),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }



}
