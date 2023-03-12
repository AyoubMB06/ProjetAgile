package backend.exceptions.definition.Candidat;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class CandidatNotFoundException extends ApiException {
    public CandidatNotFoundException(Environment env) {
        super(env);
    }

    public CandidatNotFoundException() {
        super();
    }

}
