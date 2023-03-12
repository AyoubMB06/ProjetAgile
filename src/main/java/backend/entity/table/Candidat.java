package backend.entity.table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "CANDIDAT")
public class Candidat {
    @Id
    @Size(max = 50)
    @Column(name = "NO_CANDIDAT", nullable = false, length = 50)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "ANNEE_UNIVERSITAIRE", referencedColumnName = "ANNEE_UNIVERSITAIRE", nullable = false),
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false)
    })
    private Promotion promotion;

    @Size(max = 50)
    @NotNull
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Size(max = 50)
    @NotNull
    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Size(max = 1)
    @NotNull
    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;

    @NotNull
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDate dateNaissance;

    @Size(max = 255)
    @NotNull
    @Column(name = "LIEU_NAISSANCE", nullable = false)
    private String lieuNaissance;

    @Size(max = 50)
    @NotNull
    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Size(max = 20)
    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    @Size(max = 20)
    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Size(max = 255)
    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "ADRESSE", nullable = false)
    private String adresse;

    @Size(max = 10)
    @Column(name = "CODE_POSTAL", length = 10)
    private String codePostal;

    @Size(max = 255)
    @NotNull
    @Column(name = "VILLE", nullable = false)
    private String ville;

    @Size(max = 5)
    @NotNull
    @Column(name = "PAYS_ORIGINE", nullable = false, length = 5)
    private String paysOrigine;

    @Size(max = 6)
    @NotNull
    @Column(name = "UNIVERSITE_ORIGINE", nullable = false, length = 6)
    private String universiteOrigine;

    @Size(max = 6)
    @Column(name = "LISTE_SELECTION", length = 6)
    private String listeSelection;

    @Column(name = "SELECTION_NO_ORDRE")
    private Long selectionNoOrdre;

    @Column(name = "CONFIRMATION_CANDIDAT")
    private String confirmationCandidat;

    @Column(name = "DATE_REPONSE_CANDIDAT")
    private LocalDate dateReponseCandidat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPaysOrigine() {
        return paysOrigine;
    }

    public void setPaysOrigine(String paysOrigine) {
        this.paysOrigine = paysOrigine;
    }

    public String getUniversiteOrigine() {
        return universiteOrigine;
    }

    public void setUniversiteOrigine(String universiteOrigine) {
        this.universiteOrigine = universiteOrigine;
    }

    public String getListeSelection() {
        return listeSelection;
    }

    public void setListeSelection(String listeSelection) {
        this.listeSelection = listeSelection;
    }

    public Long getSelectionNoOrdre() {
        return selectionNoOrdre;
    }

    public void setSelectionNoOrdre(Long selectionNoOrdre) {
        this.selectionNoOrdre = selectionNoOrdre;
    }

    public String getConfirmationCandidat() {
        return confirmationCandidat;
    }

    public void setConfirmationCandidat(String confirmationCandidat) {
        this.confirmationCandidat = confirmationCandidat;
    }

    public LocalDate getDateReponseCandidat() {
        return dateReponseCandidat;
    }

    public void setDateReponseCandidat(LocalDate dateReponseCandidat) {
        this.dateReponseCandidat = dateReponseCandidat;
    }

}