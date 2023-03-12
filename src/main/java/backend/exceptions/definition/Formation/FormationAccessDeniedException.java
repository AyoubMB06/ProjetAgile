package backend.exceptions.definition.Formation;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class FormationAccessDeniedException extends ApiException {
    public FormationAccessDeniedException(Environment env) {
        super(env);
    }

    public FormationAccessDeniedException() {
        super();
    }
}