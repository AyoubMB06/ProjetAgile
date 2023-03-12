package backend.exceptions.definition.UE;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class UENotFoundException extends ApiException {
    public UENotFoundException(Environment env) {
        super(env);
    }
    public UENotFoundException() {
        super();
    }
}
