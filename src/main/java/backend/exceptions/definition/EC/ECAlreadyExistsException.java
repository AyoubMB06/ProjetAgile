package backend.exceptions.definition.EC;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class ECAlreadyExistsException  extends ApiException {
    public ECAlreadyExistsException(Environment env) {
        super(env);
    }

    public ECAlreadyExistsException() {
        super();
    }

}
