package backend.exceptions.definition.Enseignant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EnseignantAlreadyExistException extends ApiException {
    public EnseignantAlreadyExistException(Environment env) {
        super(env);
    }
    public EnseignantAlreadyExistException() {
        super();
    }
}

