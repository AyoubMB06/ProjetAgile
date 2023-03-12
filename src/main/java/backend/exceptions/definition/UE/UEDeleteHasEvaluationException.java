package backend.exceptions.definition.UE;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class UEDeleteHasEvaluationException extends ApiException {
    public UEDeleteHasEvaluationException(Environment env) {
        super(env);
    }
    public UEDeleteHasEvaluationException() {
        super();
    }
}
