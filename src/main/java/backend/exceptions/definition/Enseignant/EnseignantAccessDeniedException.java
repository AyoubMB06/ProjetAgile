package backend.exceptions.definition.Enseignant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EnseignantAccessDeniedException extends ApiException {
    public EnseignantAccessDeniedException(Environment env) {
        super(env);
    }
    public EnseignantAccessDeniedException() {
        super();
    }
}
