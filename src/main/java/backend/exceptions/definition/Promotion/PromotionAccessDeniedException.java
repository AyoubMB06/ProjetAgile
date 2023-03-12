package backend.exceptions.definition.Promotion;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class PromotionAccessDeniedException extends ApiException {
    public PromotionAccessDeniedException(Environment env) {
        super(env);
    }
    public PromotionAccessDeniedException() {
        super();
    }
}