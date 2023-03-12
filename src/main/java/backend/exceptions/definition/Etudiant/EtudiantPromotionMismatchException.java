package backend.exceptions.definition.Etudiant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EtudiantPromotionMismatchException extends ApiException {
    public EtudiantPromotionMismatchException(Environment env) {
        super(env);
    }

    public EtudiantPromotionMismatchException() {
        super();
    }

}