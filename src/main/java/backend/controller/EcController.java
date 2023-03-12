package backend.controller;

import backend.service.EcService;
import backend.service.FormationService;
import lombok.RequiredArgsConstructor;
import openAPI.api.EcsApi;
import openAPI.model.DeleteEC200Response;
import openAPI.model.ListECs;
import openAPI.model.XElementConstitutif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequiredArgsConstructor
public class EcController implements EcsApi {

    @Autowired
    EcService ecService;

    @Override
    public ResponseEntity<ListECs> getECs(String formation, String ue, Integer page, Integer size) {
            return ResponseEntity.ok(ecService.getECs(formation, ue, page, size));
    }

    @Override
    public ResponseEntity<XElementConstitutif> getEC(String codeFormation, String codeUe, String codeEc) {
        return ResponseEntity.ok(ecService.findEC(codeFormation, codeUe, codeEc));
    }

    @Override
    public ResponseEntity<XElementConstitutif> createEC(XElementConstitutif xelementConstitutif) {
        return ResponseEntity.ok(ecService.createEC(xelementConstitutif));
    }

    @Override
    public ResponseEntity<XElementConstitutif> updateEC(String codeFormation, String codeUe, String codeEc, XElementConstitutif xelementConstitutif) {
        return ResponseEntity.ok(ecService.updateEC(codeFormation, codeUe, codeEc, xelementConstitutif));
    }

    @Override
    public ResponseEntity<DeleteEC200Response> deleteEC(String codeFormation, String codeUe, String codeEc) {
        return ResponseEntity.ok(ecService.deleteEC(codeFormation, codeUe, codeEc));
    }


}