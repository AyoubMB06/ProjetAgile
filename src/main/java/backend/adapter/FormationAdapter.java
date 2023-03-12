package backend.adapter;

import backend.entity.table.Formation;
import openAPI.model.XFormation;

import java.math.BigDecimal;

public class FormationAdapter extends Formation {

    public FormationAdapter(Formation formation){

        this.setId(formation.getId());
        this.setDiplome(formation.getDiplome());
        this.setN0Annee(formation.getN0Annee());
        this.setNomFormation(formation.getNomFormation());
        this.setDoubleDiplome(formation.getDoubleDiplome());
        this.setDebutAccreditation(formation.getDebutAccreditation());
        this.setFinAccreditation(formation.getFinAccreditation());
    }

    public FormationAdapter(XFormation formation){

        this.setId(formation.getCodeFormation());
        this.setDiplome(formation.getDiplome());
        this.setN0Annee((formation.getNoAnnee().intValue()));
        this.setNomFormation(formation.getNomFormation());
        this.setDoubleDiplome(formation.getDoubleDiplome());
        this.setDebutAccreditation(formation.getDebutAccreditation());
        this.setFinAccreditation(formation.getFinAccreditation());
    }

    public XFormation toXFormation(){

        XFormation xf = new XFormation();
        xf.setCodeFormation(this.getId());
        xf.setDiplome(this.getDiplome());
        xf.setNoAnnee(this.getN0Annee());
        xf.setNomFormation(this.getNomFormation());
        xf.setDoubleDiplome(this.getDoubleDiplome());
        xf.setDebutAccreditation(this.getDebutAccreditation());
        xf.setFinAccreditation(this.getFinAccreditation());
        return xf;
    }


    public Formation toFormation(){
        Formation f = new Formation();
        f.setId(this.getId());
        f.setDiplome(this.getDiplome());
        f.setN0Annee(this.getN0Annee());
        f.setNomFormation(this.getNomFormation());
        f.setDoubleDiplome(this.getDoubleDiplome());
        f.setDebutAccreditation(this.getDebutAccreditation());
        f.setFinAccreditation(this.getFinAccreditation());
        return f;
    }

}
