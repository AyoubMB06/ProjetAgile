package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "PROMOTION")
public class Promotion {
    @EmbeddedId
    private PromotionId id;

    @MapsId("codeFormation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false)
    private Formation codeFormation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @Size(max = 16)
    @Column(name = "SIGLE_PROMOTION", length = 16)
    private String siglePromotion;

    @NotNull
    @Column(name = "NB_MAX_ETUDIANT", nullable = false)
    private Short nbMaxEtudiant;

    @Column(name = "DATE_REPONSE_LP")
    private LocalDate dateReponseLp;

    @Column(name = "DATE_REPONSE_LALP")
    private LocalDate dateReponseLalp;

    @Column(name = "DATE_RENTREE")
    private LocalDate dateRentree;

    @Size(max = 12)
    @Column(name = "LIEU_RENTREE", length = 12)
    private String lieuRentree;

    @Size(max = 5)
    @Column(name = "PROCESSUS_STAGE", length = 5)
    private String processusStage;

    @Size(max = 255)
    @Column(name = "COMMENTAIRE")
    private String commentaire;

    public PromotionId getId() {
        return id;
    }

    public void setId(PromotionId id) {
        this.id = id;
    }

    public Formation getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(Formation codeFormation) {
        this.codeFormation = codeFormation;
    }

    public Enseignant getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(Enseignant noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    public String getSiglePromotion() {
        return siglePromotion;
    }

    public void setSiglePromotion(String siglePromotion) {
        this.siglePromotion = siglePromotion;
    }

    public Short getNbMaxEtudiant() {
        return nbMaxEtudiant;
    }

    public void setNbMaxEtudiant(Short nbMaxEtudiant) {
        this.nbMaxEtudiant = nbMaxEtudiant;
    }

    public LocalDate getDateReponseLp() {
        return dateReponseLp;
    }

    public void setDateReponseLp(LocalDate dateReponseLp) {
        this.dateReponseLp = dateReponseLp;
    }

    public LocalDate getDateReponseLalp() {
        return dateReponseLalp;
    }

    public void setDateReponseLalp(LocalDate dateReponseLalp) {
        this.dateReponseLalp = dateReponseLalp;
    }

    public LocalDate getDateRentree() {
        return dateRentree;
    }

    public void setDateRentree(LocalDate dateRentree) {
        this.dateRentree = dateRentree;
    }

    public String getLieuRentree() {
        return lieuRentree;
    }

    public void setLieuRentree(String lieuRentree) {
        this.lieuRentree = lieuRentree;
    }

    public String getProcessusStage() {
        return processusStage;
    }

    public void setProcessusStage(String processusStage) {
        this.processusStage = processusStage;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Promotion promotion = (Promotion) o;
        return getId() != null && Objects.equals(getId(), promotion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Formation getFormation() {
        return codeFormation;
    }

    public String getAnneeUniversitaire() {
        return id.getAnneeUniversitaire();
    }


}