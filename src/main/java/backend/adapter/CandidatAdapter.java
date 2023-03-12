package backend.adapter;

import backend.entity.table.Candidat;
import backend.entity.table.Promotion;
import backend.entity.table.PromotionId;
import openAPI.model.PCandidat;

import java.math.BigDecimal;

public class CandidatAdapter extends Candidat{
    public CandidatAdapter(Candidat candidat){
        this.setId(candidat.getId());
        this.setPromotion(candidat.getPromotion());
        this.setNom(candidat.getNom());
        this.setPrenom(candidat.getPrenom());
        this.setAdresse(candidat.getAdresse());
        this.setVille(candidat.getVille());
        this.setDateNaissance(candidat.getDateNaissance());
        this.setSexe(candidat.getSexe());
        this.setTelephone(candidat.getTelephone());
        this.setMobile(candidat.getMobile());
        this.setCodePostal(candidat.getCodePostal());
        this.setEmail(candidat.getEmail());
        this.setNationalite(candidat.getNationalite());
        this.setLieuNaissance(candidat.getLieuNaissance());
        this.setPaysOrigine(candidat.getPaysOrigine());
        this.setUniversiteOrigine(candidat.getUniversiteOrigine());
        this.setSelectionNoOrdre(candidat.getSelectionNoOrdre());
        this.setListeSelection(candidat.getListeSelection());
        this.setDateReponseCandidat(candidat.getDateReponseCandidat());
        this.setConfirmationCandidat(candidat.getConfirmationCandidat());
    }
    public CandidatAdapter( PCandidat candidat) {
        Promotion p = new Promotion();
        this.setId(candidat.getNoCandidat());
        this.setNom(candidat.getNom());
        this.setPrenom(candidat.getPrenom());
        p.setId(new PromotionId(candidat.getAnneeUniversitaire(),candidat.getCodeFormation()));
        this.setPromotion(p);
        this.setSexe(candidat.getSexe());
        this.setDateNaissance(candidat.getDateNaissance());
        this.setLieuNaissance(candidat.getLieuNaissance());
        this.setNationalite(candidat.getNationalite());
        this.setTelephone(candidat.getTelephone());
        this.setMobile(candidat.getMobile());
        this.setEmail(candidat.getEmail());
        this.setAdresse(candidat.getAdresse());
        this.setCodePostal(candidat.getCodePostal());
        this.setVille(candidat.getVille());
        this.setPaysOrigine(candidat.getPaysOrigine());
        this.setUniversiteOrigine(candidat.getUniversiteOrigine());
        this.setListeSelection(candidat.getListeSelection());
        this.setSelectionNoOrdre(candidat.getSelectionNoOrdre().longValue());
        this.setConfirmationCandidat(candidat.getConfirmationCandidat());
        this.setDateReponseCandidat(candidat.getDateReponseCandidat());

    }
    public PCandidat toPCandidat(){
        PCandidat candidat = new PCandidat();
        candidat.setNoCandidat(this.getId());
        candidat.setCodeFormation(this.getPromotion().getId().getCodeFormation());
        candidat.setAnneeUniversitaire(this.getPromotion().getId().getAnneeUniversitaire());
        candidat.setNom(this.getNom());
        candidat.setPrenom(this.getPrenom());
        candidat.setAdresse(this.getAdresse());
        candidat.setVille(this.getVille());
        candidat.setDateNaissance(this.getDateNaissance());
        candidat.setSexe(this.getSexe());
        candidat.setTelephone(this.getTelephone());
        candidat.setMobile(this.getMobile());
        candidat.setCodePostal(this.getCodePostal());
        candidat.setEmail(this.getEmail());
        candidat.setNationalite(this.getNationalite());
        candidat.setLieuNaissance(this.getLieuNaissance());
        candidat.setPaysOrigine(this.getPaysOrigine());
        candidat.setUniversiteOrigine(this.getUniversiteOrigine());
        candidat.setListeSelection(this.getListeSelection());

        if(this.getSelectionNoOrdre() != null)
            candidat.setSelectionNoOrdre(this.getSelectionNoOrdre().intValue());

        candidat.setConfirmationCandidat(this.getConfirmationCandidat());
        candidat.setDateReponseCandidat(this.getDateReponseCandidat());
        candidat.setListeSelection(this.getListeSelection());
        return candidat;
    }

    public Candidat toCandidat() {
        Candidat candidat = new Candidat();

        candidat.setId(this.getId());
        candidat.setNom(this.getNom());
        candidat.setPrenom(this.getPrenom());
        candidat.setPromotion(this.getPromotion());
        candidat.setSexe(this.getSexe());
        candidat.setDateNaissance(this.getDateNaissance());
        candidat.setLieuNaissance(this.getLieuNaissance());
        candidat.setNationalite(this.getNationalite());
        candidat.setTelephone(this.getTelephone());
        candidat.setMobile(this.getMobile());
        candidat.setEmail(this.getEmail());
        candidat.setAdresse(this.getAdresse());
        candidat.setCodePostal(this.getCodePostal());
        candidat.setVille(this.getVille());
        candidat.setPaysOrigine(this.getPaysOrigine());
        candidat.setUniversiteOrigine(this.getUniversiteOrigine());
        candidat.setListeSelection(this.getListeSelection());
        candidat.setSelectionNoOrdre(this.getSelectionNoOrdre());
        candidat.setConfirmationCandidat(this.getConfirmationCandidat());
        candidat.setDateReponseCandidat(this.getDateReponseCandidat());
        return candidat;
    }
}

