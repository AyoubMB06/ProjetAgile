package backend.adapter;

import backend.entity.table.Enseignant;
import openAPI.model.GEnseignant;
import openAPI.model.PEnseignant;

// Ce adapteur permet to convertir un objet de type Enseignant en objet de type:
// - GEnseignant : un enseignant public (exposer à l'extérieur)
// - PEnseignant : un enseignant privé (utilisé en interne par l'ADM)
// - ainsi que l'inverse.
public class EnseignantAdapter extends Enseignant {
    public EnseignantAdapter(Enseignant enseignant) {

        this.setId(enseignant.getId());
        this.setNom(enseignant.getNom());
        this.setPrenom(enseignant.getPrenom());
        this.setSexe(enseignant.getSexe());
        this.setAdresse(enseignant.getAdresse());
        this.setCodePostal(enseignant.getCodePostal());
        this.setVille(enseignant.getVille());
        this.setPays(enseignant.getPays());
        this.setType(enseignant.getType());
        this.setEmailPerso(enseignant.getEmailPerso());
        this.setEmailUbo(enseignant.getEmailUbo());
        this.setTelephone(enseignant.getTelephone());
        this.setMobile(enseignant.getMobile());


    }
    public EnseignantAdapter(PEnseignant enseignant) {
        this.setId(enseignant.getNoEnseignant());
        this.setNom(enseignant.getNom());
        this.setPrenom(enseignant.getPrenom());
        this.setSexe(enseignant.getSexe());
        this.setAdresse(enseignant.getAdresse());
        this.setCodePostal(enseignant.getCodePostal());
        this.setVille(enseignant.getVille());
        this.setPays(enseignant.getPays());
        this.setType(enseignant.getType());
        this.setEmailPerso(enseignant.getEmailPerso());
        this.setEmailUbo(enseignant.getEmailUbo());
        this.setTelephone(enseignant.getTelephone());
        this.setMobile(enseignant.getMobile());
    }

    public PEnseignant toPEnseignant() {
        PEnseignant ens = new PEnseignant();
        ens.setNoEnseignant(this.getId());
        ens.setNom(this.getNom());
        ens.setPrenom(this.getPrenom());
        ens.setAdresse(this.getAdresse());
        ens.setCodePostal(this.getCodePostal());
        ens.setVille(this.getVille());
        ens.setPays(this.getPays());
        ens.setType(this.getType());
        ens.setEmailPerso(this.getEmailPerso());
        ens.setEmailUbo(this.getEmailUbo());
        ens.setTelephone(this.getTelephone());
        ens.setMobile(this.getMobile());
        ens.setSexe(this.getSexe());
        return ens;
    }

    public GEnseignant toGEnseignant() {
        GEnseignant ens = new GEnseignant();
        ens.setNoEnseignant(this.getId());
        ens.setNom(this.getNom());
        ens.setPrenom(this.getPrenom());
        ens.setEmailUbo(this.getEmailUbo());
        return ens;
    }

    public Enseignant toEnseignant() {
        Enseignant ens = new Enseignant();
        ens.setId(this.getId());
        ens.setNom(this.getNom());
        ens.setPrenom(this.getPrenom());
        ens.setSexe(this.getSexe());
        ens.setAdresse(this.getAdresse());
        ens.setCodePostal(this.getCodePostal());
        ens.setVille(this.getVille());
        ens.setPays(this.getPays());
        ens.setType(this.getType());
        ens.setEmailPerso(this.getEmailPerso());
        ens.setEmailUbo(this.getEmailUbo());
        ens.setTelephone(this.getTelephone());
        ens.setMobile(this.getMobile());
        return ens;
    }


}
