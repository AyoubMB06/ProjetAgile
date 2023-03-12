package backend.entity.table;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DroitId implements Serializable {
    private static final long serialVersionUID = -59524696677079110L;
    @NotNull
    @Column(name = "ID_EVALUATION", nullable = false)
    private Integer idEvaluation;

    @NotNull
    @Column(name = "NO_ENSEIGNANT", nullable = false)
    private Short noEnseignant;

    public Integer getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Short getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(Short noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DroitId entity = (DroitId) o;
        return Objects.equals(this.idEvaluation, entity.idEvaluation) &&
                Objects.equals(this.noEnseignant, entity.noEnseignant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvaluation, noEnseignant);
    }

}