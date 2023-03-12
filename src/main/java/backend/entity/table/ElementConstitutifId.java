package backend.entity.table;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ElementConstitutifId implements Serializable {
    private static final long serialVersionUID = -4117080861565745245L;
    @Size(max = 8)
    @NotNull
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;

    @Size(max = 8)
    @NotNull
    @Column(name = "CODE_UE", nullable = false, length = 8)
    private String codeUe;

    @Size(max = 8)
    @NotNull
    @Column(name = "CODE_EC", nullable = false, length = 8)
    private String codeEc;

    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public String getCodeUe() {
        return codeUe;
    }

    public void setCodeUe(String codeUe) {
        this.codeUe = codeUe;
    }

    public String getCodeEc() {
        return codeEc;
    }

    public void setCodeEc(String codeEc) {
        this.codeEc = codeEc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ElementConstitutifId entity = (ElementConstitutifId) o;
        return Objects.equals(this.codeEc, entity.codeEc) &&
                Objects.equals(this.codeUe, entity.codeUe) &&
                Objects.equals(this.codeFormation, entity.codeFormation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeEc, codeUe, codeFormation);
    }

}