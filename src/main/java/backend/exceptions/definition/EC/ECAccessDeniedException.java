package backend.exceptions.definition.EC;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class ECAccessDeniedException extends ApiException {
    public ECAccessDeniedException(Environment env) {
        super(env);
    }

    public ECAccessDeniedException() {
        super();
    }

}
