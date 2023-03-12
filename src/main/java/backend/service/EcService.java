package backend.service;

import backend.adapter.ElementConstitutifAdapter;
import backend.entity.table.ElementConstitutif;
import backend.entity.table.Enseignant;
import backend.entity.table.Formation;
import backend.entity.table.UniteEnseignement;
import backend.exceptions.definition.EC.*;
import backend.exceptions.definition.Enseignant.EnseignantNotFoundException;
import backend.exceptions.definition.Formation.FormationNotFoundException;
import backend.exceptions.definition.UE.UENotFoundException;
import backend.repository.table.ElementConstitutifRepository;
import backend.repository.table.EnseignantRepository;
import backend.repository.table.FormationRepository;
import backend.repository.table.UniteEnseignementRepository;
import openAPI.model.DeleteEC200Response;
import openAPI.model.ListECs;
import openAPI.model.XElementConstitutif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcService {

    @Autowired
    private final FormationRepository formationRepository;
    @Autowired
    private final ElementConstitutifRepository elementConstitutifRepository;
    @Autowired
    private UniteEnseignementRepository uniteEnseignementRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private AuthService authService;

    public EcService(FormationRepository formationRepository, ElementConstitutifRepository elementConstitutifRepository) {
        this.formationRepository = formationRepository;
        this.elementConstitutifRepository = elementConstitutifRepository;
    }

    public ListECs getECs(String code_formation, String code_ue, Integer page, Integer size) {

        List<XElementConstitutif> xElementConstitutif;
        List<ElementConstitutif> l = elementConstitutifRepository.findAll().stream().toList();
        // filter l where code_formation == code_formation
        if(code_formation != null) {
            l = l.stream().filter(ec -> ec.getUniteEnseignement().getId().getCodeFormation().equalsIgnoreCase(code_formation)).toList();
        }
        if (code_ue != null) {
            l = l.stream().filter(ec -> ec.getUniteEnseignement().getId().getCodeUe().equalsIgnoreCase(code_ue)).toList();
        }
        // split l into n page of size=size
        int start = page * size;
        int end = Math.min(start + size, l.size());
        l = l.subList(start, end);
        // convert l to xElementConstitutif
        xElementConstitutif = l.stream().map(ElementConstitutifAdapter::toXSElementConstitutif).toList();
        return new ListECs().data(xElementConstitutif).total(xElementConstitutif.size());

    }

    public XElementConstitutif findEC(String codeFormation, String codeUe, String codeEc) {

        ElementConstitutif ec = getEC(codeFormation, codeUe, codeEc);
        return new ElementConstitutifAdapter(ec).toXElementConstitutif();
    }

    public XElementConstitutif createEC(XElementConstitutif xEC) {

        if(!authService.isAdm()) throw new ECAccessDeniedException(); // throw exception AccessDeniedException

        // check if EC exists
        xEC.setCodeFormation(xEC.getCodeFormation().toUpperCase());
        xEC.setCodeUe(xEC.getCodeUe().toUpperCase());
        xEC.setCodeEc(xEC.getCodeEc().toUpperCase());
        try {
            getEnseignant(xEC.getNoEnseignant()); // check if enseignant exists
            getEC(xEC.getCodeFormation(), xEC.getCodeUe(), xEC.getCodeEc()); // check if EC exists
        } catch (ECNotFoundException e) {
            ElementConstitutif ec = new ElementConstitutifAdapter(xEC).toElementConstitutif();
            ec = elementConstitutifRepository.save(ec);
            return new ElementConstitutifAdapter(ec).toXElementConstitutif();
        }
        throw new ECAlreadyExistsException();

    }

    public XElementConstitutif updateEC(String codeFormation, String codeUe, String codeEc, XElementConstitutif xelementConstitutif) {

        if(!authService.isAdm()) throw new ECAccessDeniedException(); // throw exception AccessDeniedException

        xelementConstitutif.setCodeFormation(xelementConstitutif.getCodeFormation().toUpperCase());
        xelementConstitutif.setCodeUe(xelementConstitutif.getCodeUe().toUpperCase());
        xelementConstitutif.setCodeEc(xelementConstitutif.getCodeEc().toUpperCase());
        codeFormation = codeFormation.toUpperCase();
        codeUe = codeUe.toUpperCase();
        codeEc = codeEc.toUpperCase();

        if (!codeFormation.equals(xelementConstitutif.getCodeFormation()) || !codeUe.equals(xelementConstitutif.getCodeUe()) || !codeEc.equals(xelementConstitutif.getCodeEc())) {
            throw new ECNotMatchException(); // throw exception ECIdNotMatchException
        }

        getEC(codeFormation, codeUe, codeEc); // this will throw exception if EC not found
        getEnseignant(xelementConstitutif.getNoEnseignant()); // check if enseignant exists
        ElementConstitutif ecUpdated = new ElementConstitutifAdapter(xelementConstitutif).toElementConstitutif();
        ecUpdated = elementConstitutifRepository.save(ecUpdated);
        return new ElementConstitutifAdapter(ecUpdated).toXElementConstitutif();
    }

    public DeleteEC200Response deleteEC(String codeFormation, String codeUe, String codeEc) {

        if(!authService.isAdm()) throw new ECAccessDeniedException(); // throw exception AccessDeniedException

        DeleteEC200Response response = new DeleteEC200Response();
        codeFormation = codeFormation.toUpperCase();
        codeUe = codeUe.toUpperCase();
        codeEc = codeEc.toUpperCase();
        ElementConstitutif ec = getEC(codeFormation, codeUe, codeEc); // this will throw exception if EC not found

        if (!elementConstitutifRepository.getEvaluationsOfEC(codeFormation, codeUe, codeEc).isEmpty())
            throw new ECDeleteHasEvaluationException();

        elementConstitutifRepository.delete(ec);
        return response.codeEc(codeEc).codeUe(codeUe).codeFormation(codeFormation);
    }


    // Fonctions
    public Formation getFormation(String codeFormation) {
        codeFormation = codeFormation.toUpperCase();
        Formation form = formationRepository.findByIdLikeIgnoreCase(codeFormation);
        if (form == null) throw new FormationNotFoundException();
        return form;
    }

    public UniteEnseignement getUE(String codeFormation, String codeUe) {
        codeFormation = codeFormation.toUpperCase();
        codeUe = codeUe.toUpperCase();
        getFormation(codeFormation); // this will throw exception if formation not found
        UniteEnseignement ue = uniteEnseignementRepository.findUE(codeFormation, codeUe);
        if (ue == null) throw new UENotFoundException();
        return ue;
    }

    public ElementConstitutif getEC(String codeFormation, String codeUe, String codeEc) {
        codeFormation=codeFormation.toUpperCase();
        codeUe=codeUe.toUpperCase();
        codeEc=codeEc.toUpperCase();
        getUE(codeFormation, codeUe); // this will throw exception if Formation OR UE not found
        ElementConstitutif ec = elementConstitutifRepository.findByPk(codeFormation, codeUe, codeEc);
        if (ec == null) throw new ECNotFoundException();
        return ec;
    }

    public Enseignant getEnseignant(Integer noEnseignant) {
        Enseignant enseignant = enseignantRepository.findById(noEnseignant).orElse(null);
        if (enseignant == null) throw new EnseignantNotFoundException();
        return enseignant;
    }





}