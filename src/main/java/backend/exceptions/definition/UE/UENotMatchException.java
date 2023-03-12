package backend.exceptions.definition.UE;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class UENotMatchException extends ApiException {
    public UENotMatchException(Environment env) {
        super(env);
    }
    public UENotMatchException() {
        super();
    }
}