package backend.exceptions.definition.Candidat;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class CandidatAlreadyExistsException extends ApiException {
    public CandidatAlreadyExistsException(Environment env) {
        super(env);
    }

    public CandidatAlreadyExistsException() {
        super();
    }

}