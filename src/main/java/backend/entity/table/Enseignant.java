package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ENSEIGNANT")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENS_SEQ")
    @SequenceGenerator(name = "ENS_SEQ", sequenceName = "ENS_SEQ", allocationSize = 1)
    @Column(name = "NO_ENSEIGNANT", nullable = false, insertable = false, updatable = false)
    private Integer id;

    @Size(max = 5)
    @NotNull
    @Column(name = "TYPE", nullable = false, length = 5)
    private String type;

    @Size(max = 1)
    @NotNull
    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;

    @Size(max = 50)
    @NotNull
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Size(max = 50)
    @NotNull
    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Size(max = 255)
    @NotNull
    @Column(name = "ADRESSE", nullable = false)
    private String adresse;

    @Size(max = 10)
    @NotNull
    @Column(name = "CODE_POSTAL", nullable = false, length = 10)
    private String codePostal;

    @Size(max = 255)
    @NotNull
    @Column(name = "VILLE", nullable = false)
    private String ville;

    @Size(max = 5)
    @NotNull
    @Column(name = "PAYS", nullable = false, length = 5)
    private String pays;

    @Size(max = 20)
    @NotNull
    @Column(name = "MOBILE", nullable = false, length = 20)
    private String mobile;

    @Size(max = 20)
    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    @Size(max = 255)
    @NotNull
    @Column(name = "EMAIL_UBO", nullable = false)
    private String emailUbo;

    @Size(max = 255)
    @Column(name = "EMAIL_PERSO")
    private String emailPerso;

    @OneToMany(mappedBy = "noEnseignant", orphanRemoval = true)
    private Set<Promotion> promotions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "noEnseignant", orphanRemoval = true)
    private Set<ElementConstitutif> elementConstitutifs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "noEnseignant", orphanRemoval = true)
    private Set<UniteEnseignement> uniteEnseignements = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmailUbo() {
        return emailUbo;
    }

    public void setEmailUbo(String emailUbo) {
        this.emailUbo = emailUbo;
    }

    public String getEmailPerso() {
        return emailPerso;
    }

    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Set<ElementConstitutif> getElementConstitutifs() {
        return elementConstitutifs;
    }

    public void setElementConstitutifs(Set<ElementConstitutif> elementConstitutifs) {
        this.elementConstitutifs = elementConstitutifs;
    }

    public Set<UniteEnseignement> getUniteEnseignements() {
        return uniteEnseignements;
    }

    public void setUniteEnseignements(Set<UniteEnseignement> uniteEnseignements) {
        this.uniteEnseignements = uniteEnseignements;
    }
}