package backend.adapter;


import backend.entity.table.Enseignant;
import backend.entity.table.Formation;
import backend.entity.table.UniteEnseignement;
import backend.entity.table.UniteEnseignementId;
import openAPI.model.XUniteEnseignement;

public class UniteEnseignementAdapter extends UniteEnseignement{
    public UniteEnseignementAdapter(UniteEnseignement UE) {
        this.setId(UE.getId());
        this.setNoEnseignant(UE.getNoEnseignant());
        this.setCodeFormation(UE.getCodeFormation());
        this.setDesignation(UE.getDesignation());
        this.setSemestre(UE.getSemestre());
        this.setDescription(UE.getDescription());
        this.setNbhCm(UE.getNbhCm());
        this.setNbhTd(UE.getNbhTd());
        this.setNbhTp(UE.getNbhTp());
    }
    public UniteEnseignementAdapter(XUniteEnseignement xUE) {
        this.setId(new UniteEnseignementId(xUE.getCodeFormation(), xUE.getCodeUe()));

        Enseignant ens = new Enseignant();
        ens.setId(xUE.getNoEnseignant());

        this.setNoEnseignant(ens);
        Formation frm = new Formation();
        frm.setId(xUE.getCodeFormation());
        this.setCodeFormation(frm);
        this.setDesignation(xUE.getDesignation());
        this.setSemestre(xUE.getSemestre());
        this.setDescription(xUE.getDescription());
        this.setNbhCm(xUE.getNbhCm().longValue());
        this.setNbhTd(xUE.getNbhTd().shortValue());
        this.setNbhTp(xUE.getNbhTp().shortValue());
    }
    public XUniteEnseignement toXUniteEnseignement() {
        XUniteEnseignement xUE = new XUniteEnseignement();
        xUE.setCodeFormation(this.getId().getCodeFormation());
        xUE.setCodeUe(this.getId().getCodeUe());
        xUE.setNoEnseignant(this.getNoEnseignant().getId());
        xUE.setDesignation(this.getDesignation());
        xUE.setSemestre(this.getSemestre());
        xUE.setDescription(this.getDescription());
        xUE.setNbhCm(Math.toIntExact(this.getNbhCm()));
        xUE.setNbhTd(this.getNbhTd().intValue());
        xUE.setNbhTp(this.getNbhTp().intValue());
        return xUE;
    }
    public static XUniteEnseignement toXUniteEnseignementm(UniteEnseignement UE) {
        return new UniteEnseignementAdapter(UE).toXUniteEnseignement();
    }

    public UniteEnseignement toUniteEnseignement() {
        UniteEnseignement UE = new UniteEnseignement();
        UE.setId(new UniteEnseignementId(this.getId().getCodeFormation(), this.getId().getCodeUe()));
        UE.setNoEnseignant(this.getNoEnseignant());
        UE.setCodeFormation(this.getCodeFormation());
        UE.setDesignation(this.getDesignation());
        UE.setSemestre(this.getSemestre());
        UE.setDescription(this.getDescription());
        UE.setNbhCm(this.getNbhCm());
        UE.setNbhTd(this.getNbhTd());
        UE.setNbhTp(this.getNbhTp());
        return UE;
    }

}