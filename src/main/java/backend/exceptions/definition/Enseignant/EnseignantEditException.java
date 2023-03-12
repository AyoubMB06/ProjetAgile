package backend.exceptions.definition.Enseignant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EnseignantEditException extends ApiException {
    public EnseignantEditException(Environment env) {
        super(env);
    }
    public EnseignantEditException() {
        super();
    }
}
