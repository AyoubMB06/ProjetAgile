package backend.exceptions.advice;

import backend.exceptions.definition.ApiException;
import openAPI.model.NotOkayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(2)
public class ApiExceptionHandler {
    @Autowired
    private Environment env;
    @ExceptionHandler({ApiException.class })
    public ResponseEntity<NotOkayMessage> handleApiException(
            ApiException ex, WebRequest request) {
        ex.setEnv(env);

        return new ResponseEntity<>(
                ex.getNotOkayMessage(),
                new HttpHeaders(), ex.getStatusCode());
    }
}
