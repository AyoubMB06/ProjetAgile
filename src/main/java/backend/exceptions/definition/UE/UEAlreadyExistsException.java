package backend.exceptions.definition.UE;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class UEAlreadyExistsException extends ApiException {
    public UEAlreadyExistsException(Environment env) {
        super(env);
    }
    public UEAlreadyExistsException() {
        super();
    }
}