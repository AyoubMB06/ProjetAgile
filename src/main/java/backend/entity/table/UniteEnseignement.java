package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "UNITE_ENSEIGNEMENT")
public class UniteEnseignement {
    @EmbeddedId
    private UniteEnseignementId id;

    @MapsId("codeFormation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false)
    private Formation codeFormation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @Size(max = 64)
    @NotNull
    @Column(name = "DESIGNATION", nullable = false, length = 64)
    private String designation;

    @Size(max = 3)
    @NotNull
    @Column(name = "SEMESTRE", nullable = false, length = 3)
    private String semestre;

    @Size(max = 256)
    @Column(name = "DESCRIPTION", length = 256)
    private String description;

    @Column(name = "NBH_CM")
    private Long nbhCm;

    @Column(name = "NBH_TD")
    private Short nbhTd;

    @Column(name = "NBH_TP")
    private Short nbhTp;

    public Set<ElementConstitutif> getElementConstitutifs() {
        return elementConstitutifs;
    }

    public void setElementConstitutifs(Set<ElementConstitutif> elementConstitutifs) {
        this.elementConstitutifs = elementConstitutifs;
    }

    @OneToMany(mappedBy = "uniteEnseignement", orphanRemoval = true)
    private Set<ElementConstitutif> elementConstitutifs = new LinkedHashSet<>();

    public UniteEnseignementId getId() {
        return id;
    }

    public void setId(UniteEnseignementId id) {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNbhCm() {
        return nbhCm;
    }

    public void setNbhCm(Long nbhCm) {
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



}