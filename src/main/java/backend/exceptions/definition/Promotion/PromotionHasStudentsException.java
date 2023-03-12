package backend.exceptions.definition.Promotion;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class PromotionHasStudentsException  extends ApiException {
    public PromotionHasStudentsException(Environment env) {
        super(env);
    }
    public PromotionHasStudentsException() {
        super();
    }
}
