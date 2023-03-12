package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "EVALUATION")
public class Evaluation {
    @Id
    @Column(name = "ID_EVALUATION", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "ANNEE_UNIVERSITAIRE", referencedColumnName = "ANNEE_UNIVERSITAIRE", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false, updatable = false, insertable = false),
    })
    private Promotion promotion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "CODE_EC", referencedColumnName = "CODE_EC", nullable = false, updatable = false, insertable = false)

    })
    private ElementConstitutif elementConstitutif;

    @NotNull
    @Column(name = "CODE_UE", nullable = true)
    private String CodeUe;

    @NotNull
    @Column(name = "CODE_EC", nullable = true)
    private String CodeEc;

    @NotNull
    @Column(name = "NO_EVALUATION", nullable = false)
    private Short noEvaluation;

    @Size(max = 16)
    @NotNull
    @Column(name = "DESIGNATION", nullable = false, length = 16)
    private String designation;

    @Size(max = 3)
    @NotNull
    @Column(name = "ETAT", nullable = false, length = 3)
    private String etat;

    @Size(max = 64)
    @Column(name = "PERIODE", length = 64)
    private String periode;

    @NotNull
    @Column(name = "DEBUT_REPONSE", nullable = false)
    private LocalDate debutReponse;

    @NotNull
    @Column(name = "FIN_REPONSE", nullable = false)
    private LocalDate finReponse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enseignant getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(Enseignant noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public ElementConstitutif getElementConstitutif() {
        return elementConstitutif;
    }

    public void setElementConstitutif(ElementConstitutif elementConstitutif) {
        this.elementConstitutif = elementConstitutif;
    }

    public Short getNoEvaluation() {
        return noEvaluation;
    }

    public void setNoEvaluation(Short noEvaluation) {
        this.noEvaluation = noEvaluation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public LocalDate getDebutReponse() {
        return debutReponse;
    }

    public void setDebutReponse(LocalDate debutReponse) {
        this.debutReponse = debutReponse;
    }

    public LocalDate getFinReponse() {
        return finReponse;
    }

    public void setFinReponse(LocalDate finReponse) {
        this.finReponse = finReponse;
    }

}