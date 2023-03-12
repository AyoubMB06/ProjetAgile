package backend.controller;

import backend.service.EcService;
import backend.service.FormationService;
import openAPI.api.FormationsApi;
import openAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FormationController implements FormationsApi {

    @Autowired
    FormationService formationService;

    @Autowired
    EcService ecService;

    @Override
    public ResponseEntity<XFormation> creerFormation(XFormation xformation) {
        return ResponseEntity.ok(formationService.createFormation(xformation));
    }

    @Override
    public ResponseEntity<DeleteFormationVierge200Response> deleteFormationVierge(String codeFormation) {
        DeleteFormationVierge200Response deleteFormationVierge200Response = new DeleteFormationVierge200Response();
        deleteFormationVierge200Response.setCodeFormation(formationService.deleteFormationById(codeFormation));

        return     ResponseEntity.ok(deleteFormationVierge200Response);
    }

    @Override
    public ResponseEntity<XElementConstitutif> getECBack(String codeFormation, String codeUe, String codeEc) {
        return ResponseEntity.ok(formationService.getECBack(codeFormation,codeUe,codeEc));
    }

    @Override
    public ResponseEntity<ListECs> getECsByFormAndUE(String codeFormation, String codeUe) {
        return ResponseEntity.ok(formationService.getECsByFormAndUE(codeFormation,codeUe));
    }

    @Override
    public ResponseEntity<XFormation> getFormation(String codeFormation) {
        return ResponseEntity.ok(formationService.findFormationById(codeFormation));
    }

    @Override
    public ResponseEntity<ListFormations> getFormations(Integer page, Integer size) {
        return ResponseEntity.ok(formationService.findFormations(page,size));
    }

    @Override
    public ResponseEntity<XUniteEnseignement> getUEFormation(String codeFormation, String codeUe) {
        return null;
    }

    @Override
    public ResponseEntity<List<XUniteEnseignement>> getUEsOfFormation(String codeFormation, Integer page, Integer size) {
        return null;
    }
}