package backend.adapter;

import backend.entity.table.Etudiant;
import backend.entity.table.Formation;
import backend.entity.table.Promotion;
import backend.entity.table.PromotionId;
import openAPI.model.GEtudiant;
import openAPI.model.PEtudiant;
import openAPI.model.XPromotion;

import java.math.BigDecimal;

public class EtudiantAdapter extends Etudiant {
    public EtudiantAdapter(Etudiant etudiant){
        this.setId(etudiant.getId());
        this.setNom(etudiant.getNom());
        this.setPrenom(etudiant.getPrenom());
        this.setAdresse(etudiant.getAdresse());
        this.setVille(etudiant.getVille());
        this.setDateNaissance(etudiant.getDateNaissance());
        this.setPromotion(etudiant.getPromotion());
        this.setSexe(etudiant.getSexe());
        this.setTelephone(etudiant.getTelephone());
        this.setMobile(etudiant.getMobile());
        this.setEmailUbo(etudiant.getEmailUbo());
        this.setCodePostal(etudiant.getCodePostal());
        this.setEmail(etudiant.getEmail());
        this.setNationalite(etudiant.getNationalite());
        this.setLieuNaissance(etudiant.getLieuNaissance());
        this.setPaysOrigine(etudiant.getPaysOrigine());
        this.setUniversiteOrigine(etudiant.getUniversiteOrigine());
        this.setGroupeTp(etudiant.getGroupeTp());
        this.setGroupeAnglais(etudiant.getGroupeAnglais());
    }
    public EtudiantAdapter (PEtudiant etudiant){
        Promotion promo = new Promotion();
        Formation frm =new Formation();
        this.setId(etudiant.getNoEtudiant());
        promo.setId(new PromotionId(etudiant.getAnneeUniversitaire(),etudiant.getCodeFormation()));
        frm.setId(etudiant.getCodeFormation());
        promo.setCodeFormation(frm);
        this.setPromotion(promo);
        this.setNom(etudiant.getNom());
        this.setPrenom(etudiant.getPrenom());
        this.setSexe(etudiant.getSexe());
        this.setDateNaissance(etudiant.getDateNaissance());
        this.setLieuNaissance(etudiant.getLieuNaissance());
        this.setNationalite(etudiant.getNationalite());
        this.setTelephone(etudiant.getTelephone());
        this.setMobile(etudiant.getMobile());
        this.setEmail(etudiant.getEmail());
        this.setEmailUbo(etudiant.getEmailUbo());
        this.setAdresse(etudiant.getAdresse());
        this.setCodePostal(etudiant.getCodePostal());
        this.setVille(etudiant.getVille());
        this.setPaysOrigine(etudiant.getPaysOrigine());
        this.setUniversiteOrigine(etudiant.getUniversiteOrigine());
        this.setGroupeTp(etudiant.getGroupeTp().longValue());
        this.setGroupeAnglais(etudiant.getGroupeAnglais().longValue());

    }
    public PEtudiant toPEtudiant(){
        PEtudiant etu = new PEtudiant();
        etu.setNoEtudiant(this.getId());
        etu.setCodeFormation(this.getPromotion().getId().getCodeFormation());
        etu.setAnneeUniversitaire(this.getPromotion().getId().getAnneeUniversitaire());
        etu.setNom(this.getNom());
        etu.setPrenom(this.getPrenom());
        etu.setAdresse(this.getAdresse());
        etu.setVille(this.getVille());
        etu.setDateNaissance(this.getDateNaissance());
        etu.setSexe(this.getSexe());
        etu.setTelephone(this.getTelephone());
        etu.setMobile(this.getMobile());
        etu.setEmailUbo(this.getEmailUbo());
        etu.setCodePostal(this.getCodePostal());
        etu.setEmail(this.getEmail());
        etu.setNationalite(this.getNationalite());
        etu.setLieuNaissance(this.getLieuNaissance());
        etu.setPaysOrigine(this.getPaysOrigine());
        etu.setUniversiteOrigine(this.getUniversiteOrigine());
        etu.setGroupeTp(BigDecimal.valueOf(this.getGroupeTp()));
        etu.setGroupeAnglais(BigDecimal.valueOf(this.getGroupeAnglais()));

        return etu;
    }
    public GEtudiant toGEtudiant(){
        GEtudiant etu = new GEtudiant();
        etu.setNoEtudiant(this.getId());
        etu.setCodeFormation(this.getPromotion().getId().getCodeFormation());
        etu.setAnneeUniversitaire(this.getPromotion().getId().getAnneeUniversitaire());
        etu.setNom(this.getNom());
        etu.setPrenom(this.getPrenom());
        etu.setDateNaissance(this.getDateNaissance());
        etu.setSexe(this.getSexe());
        etu.setEmailUbo(this.getEmailUbo());
        return etu;
    }
    public Etudiant toEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(this.getId());
        etudiant.setPromotion(this.getPromotion());
        etudiant.setNom(this.getNom());
        etudiant.setPrenom(this.getPrenom());
        etudiant.setSexe(this.getSexe());
        etudiant.setDateNaissance(this.getDateNaissance());
        etudiant.setLieuNaissance(this.getLieuNaissance());
        etudiant.setNationalite(this.getNationalite());
        etudiant.setTelephone(this.getTelephone());
        etudiant.setMobile(this.getMobile());
        etudiant.setEmail(this.getEmail());
        etudiant.setEmailUbo(this.getEmailUbo());
        etudiant.setAdresse(this.getAdresse());
        etudiant.setCodePostal(this.getCodePostal());
        etudiant.setVille(this.getVille());
        etudiant.setPaysOrigine(this.getPaysOrigine());
        etudiant.setUniversiteOrigine(this.getUniversiteOrigine());
        etudiant.setGroupeTp(this.getGroupeTp());
        etudiant.setGroupeAnglais(this.getGroupeAnglais());
        return etudiant;
    }


}
