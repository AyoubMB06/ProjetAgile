package backend.controller;

import backend.service.FormationService;
import openAPI.api.FormationsApi;
import openAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FormationController implements FormationsApi {

    @Autowired
    FormationService formationService;

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<XElementConstitutif> createEC(String codeFormation, String codeUe, XElementConstitutif xelementConstitutif) {

        return ResponseEntity.ok(formationService.createEC(xelementConstitutif));



    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<XUniteEnseignement> createUE(String codeFormation, XUniteEnseignement xuniteEnseignement) {
        return ResponseEntity.ok(formationService.createUE(xuniteEnseignement));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<XFormation> creerFormation(XFormation xformation) {

        return ResponseEntity.ok(formationService.createFormation(xformation));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<DeleteFormationVierge200Response> deleteFormationVierge(String codeFormation) {
        DeleteFormationVierge200Response deleteFormationVierge200Response = new DeleteFormationVierge200Response();
        deleteFormationVierge200Response.setCodeFormation(formationService.deleteFormationById(codeFormation));

        return     ResponseEntity.ok(deleteFormationVierge200Response);
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<DeleteEC200Response> deleteEC(String codeFormation, String codeUe, String codeEc) {
        return ResponseEntity.ok(formationService.deleteEC(codeFormation, codeUe, codeEc));
    }



    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<DeleteUE200Response> deleteUE(String codeFormation, String codeUe) {

        return ResponseEntity.ok(formationService.deleteUE(codeFormation,codeUe));
    }

    @Override
    public ResponseEntity<XElementConstitutif> getEC(String codeFormation, String codeUe, String codeEc) {
        return ResponseEntity.ok(formationService.findEC(codeFormation,codeUe,codeEc));
    }


    @Override
    public ResponseEntity<List<XElementConstitutif>> getECs(String codeFormation, String codeUe, Integer page, Integer size) {
        return ResponseEntity.ok(formationService.findECs(codeFormation,codeUe));

    }



    @Override
    public ResponseEntity<XFormation> getFormation(String codeFormation) {

        return ResponseEntity.ok(formationService.findFormationById(codeFormation));

    }

    @Override
    public ResponseEntity<List<XFormation>> getFormations(Integer page, Integer size) {

        return ResponseEntity.ok(formationService.findFormations(page, size));

    }

    @Override
    public ResponseEntity<XUniteEnseignement> getUE(String codeFormation, String codeUe) {
        return ResponseEntity.ok(formationService.findUE(codeFormation,codeUe));

    }

    @Override
    public ResponseEntity<List<XUniteEnseignement>> getUEs(String codeFormation, Integer page, Integer size) {
        return ResponseEntity.ok(formationService.findByUeByCodeFormation(codeFormation));

    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<XElementConstitutif> updateEC(String codeFormation, String codeUe, String codeEc, XElementConstitutif xelementConstitutif) {

        return ResponseEntity.ok(formationService.updateEC(codeFormation,codeUe,codeEc,xelementConstitutif));

    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<XUniteEnseignement> updateUE(String codeFormation, String codeUe, XUniteEnseignement xuniteEnseignement) {
        return ResponseEntity.ok(formationService.updateUE( codeFormation,  codeUe,  xuniteEnseignement));
    }

}
*/