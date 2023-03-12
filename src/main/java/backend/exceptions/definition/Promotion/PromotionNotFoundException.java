package backend.exceptions.definition.Promotion;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class PromotionNotFoundException extends ApiException {
    public PromotionNotFoundException(Environment env) {
        super(env);
    }
    public PromotionNotFoundException() {
        super();
    }
}
