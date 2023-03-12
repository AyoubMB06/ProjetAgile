package backend.exceptions.definition.UE;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class UEDeleteHasECException extends ApiException {
    public UEDeleteHasECException(Environment env) {
        super(env);
    }
    public UEDeleteHasECException() {
        super();
    }
}