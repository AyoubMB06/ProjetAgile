package backend.exceptions.definition.EC;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class ECNotFoundException extends ApiException {
    public ECNotFoundException(Environment env) {
        super(env);
    }

    public ECNotFoundException() {
        super();
    }

}
