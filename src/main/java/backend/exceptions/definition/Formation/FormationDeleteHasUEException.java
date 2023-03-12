package backend.exceptions.definition.Formation;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class FormationDeleteHasUEException  extends ApiException {
    public FormationDeleteHasUEException(Environment env) {
        super(env);
    }
    public FormationDeleteHasUEException() {
        super();
    }
}