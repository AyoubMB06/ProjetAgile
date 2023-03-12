package backend.exceptions.definition.Etudiant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EtudiantNotFoundException extends ApiException {
    public EtudiantNotFoundException(Environment env) {
        super(env);
    }

    public EtudiantNotFoundException() {
        super();
    }

}