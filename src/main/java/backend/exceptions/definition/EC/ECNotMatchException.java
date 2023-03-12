package backend.exceptions.definition.EC;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class ECNotMatchException extends ApiException {

    public ECNotMatchException(Environment env) {
        super(env);
    }
    public ECNotMatchException() {
        super();
    }
}
