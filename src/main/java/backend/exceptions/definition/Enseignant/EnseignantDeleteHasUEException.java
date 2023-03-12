package backend.exceptions.definition.Enseignant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EnseignantDeleteHasUEException extends ApiException {
    public EnseignantDeleteHasUEException(Environment env) {
        super(env);
    }
    public EnseignantDeleteHasUEException() {
        super();
    }
}
