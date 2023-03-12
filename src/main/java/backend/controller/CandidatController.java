package backend.controller;

import openAPI.api.CandidatsApi;
import openAPI.model.DeleteCandidat200Response;
import openAPI.model.ListCandidats;
import openAPI.model.PCandidat;
import org.springframework.http.ResponseEntity;

public class CandidatController implements CandidatsApi {

    @Override
    public ResponseEntity<PCandidat> creerCandidat(PCandidat pcandidat) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteCandidat200Response> deleteCandidat(String noCandidat) {
        return null;
    }

    @Override
    public ResponseEntity<ListCandidats> getCandidats(Integer page, Integer size, String formation, String anneUniv) {
        return null;
    }

    @Override
    public ResponseEntity<PCandidat> getCandidatsById(String noCandidat) {
        return null;
    }

    @Override
    public ResponseEntity<PCandidat> updateCandidat(String noCandidat, PCandidat pcandidat) {
        return null;
    }
}
