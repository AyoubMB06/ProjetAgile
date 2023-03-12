package backend.exceptions.definition.Candidat;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class CandidatPromotionMismatchException extends ApiException {
    public CandidatPromotionMismatchException(Environment env) {
        super(env);
    }

    public CandidatPromotionMismatchException() {
        super();
    }

}
