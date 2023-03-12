package backend.exceptions.definition.Domain;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class DomainNotFoundException extends ApiException {
    public DomainNotFoundException(Environment env) {
        super(env);
    }

    public DomainNotFoundException() {
        super();
    }

}
