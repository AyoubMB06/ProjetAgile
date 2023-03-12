package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Range;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ELEMENT_CONSTITUTIF")
public class ElementConstitutif {
    // @EmbeddedId
  //  private ElementConstitutifId id;

    //@MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false),
            @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE", nullable = false)
    })
    private UniteEnseignement uniteEnseignement;



    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @Size(max = 64)
    @NotNull
    @Column(name = "DESIGNATION", nullable = false, length = 64)
    private String designation;

    @Size(max = 240)
    @Column(name = "DESCRIPTION", length = 240)
    private String description;

    @Column(name = "NBH_CM")
    private Short nbhCm;

    @Column(name = "NBH_TD")
    private Short nbhTd;

    @Column(name = "NBH_TP")
    private Short nbhTp;



    @Id
    @Size(max = 8)
    @NotNull
    @Column(name = "CODE_EC", nullable = false, length = 8)
    private String codeEc;


    public String getCodeEc() {
        return codeEc;
    }

    public void setCodeEc(String codeEc) {
        this.codeEc = codeEc;
    }

    public String getId() {
        return codeEc;
    }

    public void setId(String id) {
        this.codeEc = id;
    }

    public UniteEnseignement getUniteEnseignement() {
        return uniteEnseignement;
    }

    public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
        this.uniteEnseignement = uniteEnseignement;
    }

    public Enseignant getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(Enseignant noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getNbhCm() {
        return nbhCm;
    }

    public void setNbhCm(Short nbhCm) {
        this.nbhCm = nbhCm;
    }

    public Short getNbhTd() {
        return nbhTd;
    }

    public void setNbhTd(Short nbhTd) {
        this.nbhTd = nbhTd;
    }

    public Short getNbhTp() {
        return nbhTp;
    }

    public void setNbhTp(Short nbhTp) {
        this.nbhTp = nbhTp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ElementConstitutif that = (ElementConstitutif) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeEc);
    }

}