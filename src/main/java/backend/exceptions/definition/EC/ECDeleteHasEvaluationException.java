package backend.exceptions.definition.EC;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class ECDeleteHasEvaluationException  extends ApiException {
    public ECDeleteHasEvaluationException(Environment env) {
        super(env);
    }

    public ECDeleteHasEvaluationException() {
        super();
    }

}
