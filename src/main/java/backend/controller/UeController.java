package backend.controller;

import openAPI.api.UesApi;
import openAPI.model.DeleteUE200Response;
import openAPI.model.ListUEs;
import openAPI.model.XUniteEnseignement;
import org.springframework.http.ResponseEntity;

public class UeController implements UesApi {

    @Override
    public ResponseEntity<XUniteEnseignement> createUE(XUniteEnseignement xuniteEnseignement) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteUE200Response> deleteUE(String codeFormation, String codeUe) {
        return null;
    }

    @Override
    public ResponseEntity<XUniteEnseignement> getUE(String codeFormation, String codeUe) {
        return null;
    }

    @Override
    public ResponseEntity<ListUEs> getUEs(Integer page, Integer size, String formation) {
        return null;
    }

    @Override
    public ResponseEntity<XUniteEnseignement> updateUE(String codeFormation, String codeUe, XUniteEnseignement xuniteEnseignement) {
        return null;
    }
}
