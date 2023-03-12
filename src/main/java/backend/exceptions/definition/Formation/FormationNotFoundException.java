package backend.exceptions.definition.Formation;

import backend.exceptions.definition.ApiException;
import io.swagger.annotations.Api;
import org.springframework.core.env.Environment;

public class FormationNotFoundException extends ApiException {
    public FormationNotFoundException(Environment env) {
        super(env);
    }
    public FormationNotFoundException() {
        super();
    }
}
