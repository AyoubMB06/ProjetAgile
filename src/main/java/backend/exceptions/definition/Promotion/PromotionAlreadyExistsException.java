package backend.exceptions.definition.Promotion;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class PromotionAlreadyExistsException extends ApiException {
    public PromotionAlreadyExistsException(Environment env) {
        super(env);
    }
    public PromotionAlreadyExistsException() {
        super();
    }
}