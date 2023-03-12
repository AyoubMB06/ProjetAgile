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
/*
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PromotionController implements PromotionsApi {
    @Autowired
    private PromotionService promotionService;

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<PCandidat> createCandidat(String codeFormation, String anneeUniversitaire, PCandidat pcandidat) {
        return ResponseEntity.ok(promotionService.createCandidat(codeFormation, anneeUniversitaire, pcandidat));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<PEtudiant> createEtudiant(String codeFormation, String anneeUniversitaire, PEtudiant petudiant) {
        return ResponseEntity.ok(promotionService.createEtudiant(codeFormation, anneeUniversitaire, petudiant));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<XPromotion> createPromotion(XPromotion xpromo) {
        return ResponseEntity.ok(promotionService.createPromotion(xpromo));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<DeleteCandidat200Response> deleteCandidat(String codeFormation, String anneeUniversitaire, String noCandidat) {
        return ResponseEntity.ok(promotionService.deleteCandidat(codeFormation, anneeUniversitaire, noCandidat));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<DeleteEtudiant200Response> deleteEtudiant(String codeFormation, String anneeUniversitaire, String noEtudiant) {

        return ResponseEntity.ok(promotionService.deleteEtudiant(codeFormation, anneeUniversitaire, noEtudiant));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<DeletePromotion200Response> deletePromotion(String codeFormation, String anneeUniversitaire) {
        DeletePromotion200Response response = new DeletePromotion200Response();
        PromotionId validPromotionID = promotionService.deletePromotion(codeFormation,anneeUniversitaire);
        response.setAnneeUniversitaire(validPromotionID.getAnneeUniversitaire());
        response.setCodeFormation(validPromotionID.getCodeFormation());
        return ResponseEntity.ok(response);
    }


    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<OCandidat> getCandidat(String codeFormation, String anneeUniversitaire, String noCandidat) {
        return ResponseEntity.ok(promotionService.findCandidat(codeFormation, anneeUniversitaire, noCandidat));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<ListCandidats> getCandidats(String codeFormation, String anneeUniversitaire, Integer page, Integer size) {
        return ResponseEntity.ok(promotionService.findCandidats(codeFormation, anneeUniversitaire, page, size));

    }

    @Override
    @PreAuthorize("hasAuthority('ADM') or hasAuthority('ENS') or (hasAuthority('ETU') and #noEtudiant == principal.getNoEtudiant)")
    public ResponseEntity<OEtudiant> getEtudiant(String codeFormation, String anneeUniversitaire, String noEtudiant) {
        return ResponseEntity.ok(promotionService.findEtudiant(codeFormation, anneeUniversitaire, noEtudiant));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM') or hasAuthority('ENS')")
    public ResponseEntity<ListEtudiants> getEtudiants(String codeFormation, String anneeUniversitaire, Integer page, Integer size) {
        return ResponseEntity.ok(promotionService.findEtudiants(codeFormation, anneeUniversitaire, page, size));
    }

    @Override
    public ResponseEntity<XPromotion> getPromotion(String codeFormation, String anneeUniversitaire) {

        return ResponseEntity.ok(promotionService.findPromotion(codeFormation, anneeUniversitaire));

    }

    @Override
    @PreAuthorize("hasAuthority('ADM') or hasAuthority('ENS')")
    public ResponseEntity<ListPromotions> getPromotions(Integer page, Integer size) {

        return ResponseEntity.ok(promotionService.findPromotions(page, size));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<PCandidat> updateCandidat(String codeFormation, String anneeUniversitaire, String noCandidat, PCandidat pcandidat) {
        return ResponseEntity.ok(promotionService.editCandidat(codeFormation, anneeUniversitaire,noCandidat,pcandidat));
    }

    @Override
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<PEtudiant> updateEtudiant(String codeFormation, String anneeUniversitaire, String noEtudiant, PEtudiant petudiant) {
        return ResponseEntity.ok(promotionService.editEtudiant(codeFormation, anneeUniversitaire,noEtudiant,petudiant));
    }

}
*/