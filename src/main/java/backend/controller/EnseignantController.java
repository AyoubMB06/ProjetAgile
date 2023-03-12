package backend.controller;

import backend.service.EnseignantService;
import openAPI.api.EnseignantsApi;
import openAPI.model.*;
import openAPI.model.PEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EnseignantController implements EnseignantsApi {
    @Autowired
    private EnseignantService enseignantService;


    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<PEnseignant> createEnseignant(PEnseignant xenseignant) {
        return ResponseEntity.ok(enseignantService.createEnseignant(xenseignant));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<DeleteEnseignant200Response> deleteEnseignant(Integer noEnseignant) {
        DeleteEnseignant200Response response = new DeleteEnseignant200Response();
        int validNoEnseignant = enseignantService.deleteEnseignant(noEnseignant);
        response.setNoEnseignant(validNoEnseignant);
        /* should always return 200 Response, for error / fault, use Exception Handler
        /* and throws exception from Service and below.
        return
                validNoEnseignant == -1 ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);

         */
        return ResponseEntity.ok().header("Allow-Access-Control-Origin", "*").body(response);
    }

    @Override
    @PreAuthorize("hasAuthority('ADM') or hasAuthority('ENS')")
    public ResponseEntity<OEnseignant> getEnseignant(Integer noEnseignant) {

        OEnseignant xens = enseignantService.findEnseignant(noEnseignant);
        return ResponseEntity.ok(xens);
    }


    @Override
    @PreAuthorize("hasAuthority('ADM') or hasAuthority('ENS')")
    public ResponseEntity<ListEnseignants> getEnseignants(Integer start, Integer size) {
        ListEnseignants response = new ListEnseignants();
        List<OEnseignant> xens = enseignantService.findEnseignants(start, size);
        response.data(xens);
        response.total(enseignantService.countAll());
        return ResponseEntity.ok().header("Allow-Access-Control-Origin", "*").body(response);
    }

    @Override
    // To ALLOW ONLY ADM and ENS( a logged ENS must be the same as the one in the path) to access this method
   // @PreAuthorize("hasAuthority('ADM') or (hasAuthority('ENS') and #noEnseignant==principal.getNoEnseignant)")

    // To ALLOW ONLY ADM
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<PEnseignant> updateEnseignant(Integer noEnseignant,  PEnseignant xenseignant) {
        return ResponseEntity.ok(enseignantService.editEnseignant(noEnseignant, xenseignant));

    }


}
