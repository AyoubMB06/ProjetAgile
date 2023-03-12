package backend.exceptions.definition.Formation;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class FormationAlreadyExistsException extends ApiException {
    public FormationAlreadyExistsException(Environment env) {
        super(env);
    }
    public FormationAlreadyExistsException() {
        super();
    }
}