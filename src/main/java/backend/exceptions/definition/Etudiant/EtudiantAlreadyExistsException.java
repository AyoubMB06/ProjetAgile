package backend.exceptions.definition.Etudiant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EtudiantAlreadyExistsException extends ApiException {
    public EtudiantAlreadyExistsException(Environment env) {
        super(env);
    }

    public EtudiantAlreadyExistsException() {
        super();
    }

}