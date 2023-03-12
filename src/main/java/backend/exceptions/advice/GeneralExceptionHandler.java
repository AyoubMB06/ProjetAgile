package backend.exceptions.advice;

// genetrate controller advice to handle all validation errors

import backend.exceptions.definition.ApiException;
import openAPI.model.NotOkayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(3)
public class GeneralExceptionHandler {
    @Autowired
    private Environment env;
    // exception handler for MethodArgumentNotValidException
    @ExceptionHandler({Exception.class })
    public ResponseEntity<NotOkayMessage> handleApiException(
            Exception e, WebRequest request) {

        ApiException ex = new ApiException(e);
        ex.setEnv(env);

        return new ResponseEntity<>(
                ex.getNotOkayMessage(),
                new HttpHeaders(), ex.getStatusCode());
    }
    // exception handler for ConstraintViolationException
    // exception handler for MethodArgumentTypeMismatchException
    // exception handler for MissingServletRequestParameterException
    // exception handler for HttpMessageNotReadableException
    // exception handler for HttpMediaTypeNotSupportedException
    // exception handler for HttpMediaTypeNotAcceptableException
    // exception handler for MissingPathVariableException
    // exception handler for MissingServletRequestPartException
    // exception handler for BindException
    // exception handler for NoHandlerFoundException
    // exception handler for AsyncRequestTimeoutException
    // exception handler for Exception


}