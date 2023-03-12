package backend.exceptions.definition.Etudiant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EtudiantAccessDeniedException extends ApiException {
    public EtudiantAccessDeniedException(Environment env) {
        super(env);
    }

    public EtudiantAccessDeniedException() {
        super();
    }

}