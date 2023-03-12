package backend.exceptions.definition.Enseignant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EnseignantDeleteHasECException extends ApiException {
    public EnseignantDeleteHasECException(Environment env) {
        super(env);
    }
    public EnseignantDeleteHasECException() {
        super();
    }
}
