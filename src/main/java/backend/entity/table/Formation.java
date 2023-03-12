package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "FORMATION")
public class Formation {
    @Id
    @Size(max = 8)
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String id;

    @Size(max = 3)
    @NotNull
    @Column(name = "DIPLOME", nullable = false, length = 3)
    private String diplome;

    @NotNull
    @Column(name = "N0_ANNEE", nullable = false)
    private Integer n0Annee;

    @Size(max = 80)
    @NotNull
    @Column(name = "NOM_FORMATION", nullable = false, length = 80)
    private String nomFormation;

    @NotNull
    @Column(name = "DOUBLE_DIPLOME", nullable = false)
    private String doubleDiplome = "N";

    @Column(name = "DEBUT_ACCREDITATION")
    private LocalDate debutAccreditation;

    @Column(name = "FIN_ACCREDITATION")
    private LocalDate finAccreditation;

    @OneToMany(mappedBy = "codeFormation", orphanRemoval = true)
    private Set<UniteEnseignement> uniteEnseignements = new LinkedHashSet<>();

    public Set<UniteEnseignement> getUniteEnseignements() {
        return uniteEnseignements;
    }

    public void setUniteEnseignements(Set<UniteEnseignement> uniteEnseignements) {
        this.uniteEnseignements = uniteEnseignements;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    @OneToMany(mappedBy = "codeFormation", orphanRemoval = true)
    private Set<Promotion> promotions = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Integer getN0Annee() {
        return n0Annee;
    }

    public void setN0Annee(Integer n0Annee) {
        this.n0Annee = n0Annee;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public String getDoubleDiplome() {
        return doubleDiplome;
    }

    public void setDoubleDiplome(String doubleDiplome) {
        this.doubleDiplome = doubleDiplome;
    }

    public LocalDate getDebutAccreditation() {
        return debutAccreditation;
    }

    public void setDebutAccreditation(LocalDate debutAccreditation) {
        this.debutAccreditation = debutAccreditation;
    }

    public LocalDate getFinAccreditation() {
        return finAccreditation;
    }

    public void setFinAccreditation(LocalDate finAccreditation) {
        this.finAccreditation = finAccreditation;
    }

}