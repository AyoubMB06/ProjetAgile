package backend.exceptions.definition.Auth;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class NoCredentialsException extends ApiException {
    public NoCredentialsException(Environment env) {
        super(env);
    }
    public NoCredentialsException() {
        super();
    }
}
