package backend.exceptions.definition.Enseignant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EnseignantNotFoundException extends ApiException {
    public EnseignantNotFoundException(Environment env) {
        super(env);
    }
    public EnseignantNotFoundException() {
        super();
    }
}
