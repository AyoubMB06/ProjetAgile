package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "DROIT")
public class Droit {
    @EmbeddedId
    private DroitId id;

    @MapsId("idEvaluation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_EVALUATION", nullable = false)
    private Evaluation idEvaluation;

    @MapsId("noEnseignant")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @NotNull
    @Column(name = "CONSULTATION", nullable = false)
    private Boolean consultation = false;

    @NotNull
    @Column(name = "DUPLICATION", nullable = false)
    private Boolean duplication = false;

    public DroitId getId() {
        return id;
    }

    public void setId(DroitId id) {
        this.id = id;
    }

    public Evaluation getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Evaluation idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Enseignant getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(Enseignant noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    public Boolean getConsultation() {
        return consultation;
    }

    public void setConsultation(Boolean consultation) {
        this.consultation = consultation;
    }

    public Boolean getDuplication() {
        return duplication;
    }

    public void setDuplication(Boolean duplication) {
        this.duplication = duplication;
    }

}