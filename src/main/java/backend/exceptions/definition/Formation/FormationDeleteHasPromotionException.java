package backend.exceptions.definition.Formation;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class FormationDeleteHasPromotionException extends ApiException {
    public FormationDeleteHasPromotionException(Environment env) {
        super(env);
    }
    public FormationDeleteHasPromotionException() {
        super();
    }
}
