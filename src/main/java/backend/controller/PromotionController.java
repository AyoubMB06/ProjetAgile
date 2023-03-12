package backend.controller;

import backend.adapter.PromotionAdapter;
import backend.entity.table.PromotionId;
import backend.repository.table.PromotionRepository;
import backend.service.PromotionService;
import openAPI.api.PromotionsApi;
import openAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PromotionController implements PromotionsApi {


    @Override
    public ResponseEntity<XPromotion> createPromotion(XPromotion xpromotion) {
        return null;
    }

    @Override
    public ResponseEntity<DeletePromotion200Response> deletePromotion(String codeFormation, String anneeUniversitaire) {
        return null;
    }

    @Override
    public ResponseEntity<OCandidat> getCandidat(String codeFormation, String anneeUniversitaire, String noCandidat) {
        return null;
    }

    @Override
    public ResponseEntity<ListCandidats> getCandidatsPromo(String codeFormation, String anneeUniversitaire, Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<OEtudiant> getEtudiant(String codeFormation, String anneeUniversitaire, String noEtudiant) {
        return null;
    }

    @Override
    public ResponseEntity<ListEtudiants> getEtudiantsPromo(String codeFormation, String anneeUniversitaire, Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<XPromotion> getPromotion(String codeFormation, String anneeUniversitaire) {
        return null;
    }

    @Override
    public ResponseEntity<ListPromotions> getPromotions(Integer page, Integer size) {
        return null;
    }
}