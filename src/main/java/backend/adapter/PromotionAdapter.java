package backend.adapter;

import backend.entity.table.Enseignant;
import backend.entity.table.Formation;
import backend.entity.table.Promotion;
import backend.entity.table.PromotionId;
import openAPI.model.XPromotion;

import java.math.BigDecimal;

public class PromotionAdapter extends Promotion {
    public PromotionAdapter(Promotion promotion) {
        // generte setters from getters
        this.setId(promotion.getId());
        this.setCodeFormation(promotion.getCodeFormation());
        this.setNoEnseignant(promotion.getNoEnseignant());
        this.setSiglePromotion(promotion.getSiglePromotion());
        this.setNbMaxEtudiant(promotion.getNbMaxEtudiant());
        this.setDateReponseLp(promotion.getDateReponseLp());
        this.setDateReponseLalp(promotion.getDateReponseLalp());
        this.setDateRentree(promotion.getDateRentree());
        this.setSiglePromotion(promotion.getSiglePromotion());
        this.setCommentaire(promotion.getCommentaire());

    }
    // convert Promotion to XPromotion
    public XPromotion toXPromotion() {
        XPromotion xPromotion = new XPromotion();
        xPromotion.setCodeFormation(this.getId().getCodeFormation());
        xPromotion.setAnneeUniversitaire(this.getId().getAnneeUniversitaire());
        xPromotion.setSiglePromotion(this.getSiglePromotion());
        xPromotion.setNbMaxEtudiant(this.getNbMaxEtudiant().intValue());
        xPromotion.setDateReponseLp(this.getDateReponseLp());
        xPromotion.setDateReponseLalp(this.getDateReponseLalp());
        xPromotion.setDateRentree(this.getDateRentree());
        xPromotion.setSiglePromotion(this.getSiglePromotion());
        xPromotion.setNoEnseignant(this.getNoEnseignant().getId());
        xPromotion.setCommentaire(this.getCommentaire());
        return xPromotion;
    }

    public static XPromotion toXPromotion(Promotion promotion) {
        return new PromotionAdapter(promotion).toXPromotion();
    }
    public PromotionAdapter (XPromotion xpromotion){
        Formation frm = new Formation();
        Enseignant ens = new Enseignant();
         xpromotion.getNoEnseignant();
        this.setId(new PromotionId(xpromotion.getAnneeUniversitaire(),xpromotion.getCodeFormation()));
        frm.setId(xpromotion.getCodeFormation());
        this.setCodeFormation(frm);
        ens.setId(xpromotion.getNoEnseignant().intValue());
        this.setNoEnseignant(ens);
        this.setSiglePromotion(xpromotion.getSiglePromotion());
        this.setNbMaxEtudiant(xpromotion.getNbMaxEtudiant().shortValue());
        this.setDateReponseLp(xpromotion.getDateReponseLp());
        this.setDateReponseLalp(xpromotion.getDateReponseLalp());
        this.setDateRentree(xpromotion.getDateRentree());
        this.setLieuRentree(xpromotion.getLieuRentree());
        this.setProcessusStage(xpromotion.getProcessusStage());
        this.setCommentaire(xpromotion.getCommentaire());

    }

    public Promotion toPromotion() {
        Promotion promotion = new Promotion();
        promotion.setId(this.getId());
        promotion.setCodeFormation(this.getCodeFormation());
        promotion.setNoEnseignant(this.getNoEnseignant());
        promotion.setSiglePromotion(this.getSiglePromotion());
        promotion.setNbMaxEtudiant(this.getNbMaxEtudiant());
        promotion.setDateReponseLp(this.getDateReponseLp());
        promotion.setDateReponseLalp(this.getDateReponseLalp());
        promotion.setDateRentree(this.getDateRentree());
        promotion.setLieuRentree(this.getLieuRentree());
        promotion.setProcessusStage(this.getProcessusStage());
        promotion.setCommentaire(this.getCommentaire());
        return promotion;
    }
}
