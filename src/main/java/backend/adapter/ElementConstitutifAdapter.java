package backend.adapter;

import backend.entity.table.*;
import openAPI.model.XElementConstitutif;

public class ElementConstitutifAdapter extends ElementConstitutif {

    public ElementConstitutifAdapter(ElementConstitutif EC) {
        //this.setId(EC.getCodeEc());
        this.setUniteEnseignement(EC.getUniteEnseignement());
        this.setNoEnseignant(EC.getNoEnseignant());
        this.setDesignation(EC.getDesignation());
        this.setCodeEc(EC.getCodeEc());
        this.setDescription(EC.getDescription());
        this.setNbhCm(EC.getNbhCm());
        this.setNbhTd(EC.getNbhTd());
        this.setNbhTp(EC.getNbhTp());
    }

    public ElementConstitutifAdapter (XElementConstitutif xEC) {
        //this.setId(xEC.getCodeEc());
        this.setCodeEc(xEC.getCodeEc());

        Enseignant ens = new Enseignant();
        ens.setId(xEC.getNoEnseignant());

        this.setNoEnseignant(ens);

        UniteEnseignement UE = new UniteEnseignement();
        UE.setId(new UniteEnseignementId(xEC.getCodeFormation(), xEC.getCodeUe()));

        this.setUniteEnseignement(UE);
        this.setDesignation(xEC.getDesignation());
        this.setDescription(xEC.getDescription());
        this.setNbhCm(xEC.getNbhCm().shortValue());
        this.setNbhTd(xEC.getNbhTd().shortValue());
        this.setNbhTp(xEC.getNbhTp().shortValue());

    }

    public XElementConstitutif toXElementConstitutif() {
        XElementConstitutif xEC = new XElementConstitutif();
        xEC.setCodeFormation(this.getUniteEnseignement().getCodeFormation().getId());
        xEC.setCodeUe(this.getUniteEnseignement().getId().getCodeUe());
        xEC.setCodeEc(this.getCodeEc());
        xEC.setNoEnseignant(this.getNoEnseignant().getId());
        xEC.setDesignation(this.getDesignation());
        xEC.setDescription(this.getDescription());
        xEC.setNbhCm(this.getNbhCm().intValue());
        xEC.setNbhTd(this.getNbhTd().intValue());
        xEC.setNbhTp(this.getNbhTp().intValue());
        xEC.setEnseignantInfo(new EnseignantAdapter(this.getNoEnseignant()).toGEnseignant());
        return xEC;
    }

    public static XElementConstitutif toXSElementConstitutif(ElementConstitutif EC) {
        return new ElementConstitutifAdapter(EC).toXElementConstitutif();
    }

    public ElementConstitutif toElementConstitutif() {
        ElementConstitutif EC = new ElementConstitutif();
        //EC.setId(this.getId());
        EC.setCodeEc(this.getCodeEc());
        EC.setUniteEnseignement(this.getUniteEnseignement());
        EC.setNoEnseignant(this.getNoEnseignant());
        EC.setDesignation(this.getDesignation());
        EC.setDescription(this.getDescription());
        EC.setNbhCm(this.getNbhCm());
        EC.setNbhTd(this.getNbhTd());
        EC.setNbhTp(this.getNbhTp());
        return EC;
    }


}

