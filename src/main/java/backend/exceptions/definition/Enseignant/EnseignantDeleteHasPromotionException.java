package backend.exceptions.definition.Enseignant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EnseignantDeleteHasPromotionException extends ApiException {
    public EnseignantDeleteHasPromotionException(Environment env) {
        super(env);
    }
    public EnseignantDeleteHasPromotionException() {
        super();
    }
}
