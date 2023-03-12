package backend.exceptions.definition.Etudiant;

import backend.exceptions.definition.ApiException;
import org.springframework.core.env.Environment;

public class EtudiantHasReponseEvaluationException extends ApiException {
    public EtudiantHasReponseEvaluationException(Environment env) {
        super(env);
    }

    public EtudiantHasReponseEvaluationException() {
        super();
    }

}
