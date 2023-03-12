package backend.exceptions.definition.Promotion;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class PromotionHasCandidatException  extends ApiException {
    public PromotionHasCandidatException(Environment env) {
        super(env);
    }
    public PromotionHasCandidatException() {
        super();
    }
}
