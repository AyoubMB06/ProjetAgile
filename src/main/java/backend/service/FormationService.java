package backend.service;


import backend.adapter.ElementConstitutifAdapter;
import backend.adapter.FormationAdapter;
import backend.adapter.UniteEnseignementAdapter;
import backend.entity.table.*;
import backend.exceptions.definition.EC.*;
import backend.exceptions.definition.Enseignant.EnseignantNotFoundException;
import backend.exceptions.definition.Formation.*;
import backend.exceptions.definition.UE.*;
import backend.repository.table.ElementConstitutifRepository;
import backend.repository.table.EnseignantRepository;
import backend.repository.table.FormationRepository;
import backend.repository.table.UniteEnseignementRepository;
import openAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FormationService {
    //inject formation repo
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

    public FormationService(FormationRepository formationRepository, ElementConstitutifRepository elementConstitutifRepository) {

        this.formationRepository = formationRepository;
        this.elementConstitutifRepository = elementConstitutifRepository;
    }


    // ----------------- Formation ----------------- //
    public ListFormations findFormations(Integer start, Integer size) {
        ListFormations listFormations = new ListFormations();
        List<XFormation> xFormations;

        xFormations = formationRepository
                .findAll(PageRequest.of(start, size))
                .stream()
                .map(formation -> new FormationAdapter(formation).toXFormation())
                .toList();

        return new ListFormations().data(xFormations).total(xFormations.size());
    }

    public XFormation createFormation(XFormation xformation) {
        // check if formation exist
        try {
            xformation.setCodeFormation(xformation.getCodeFormation().toUpperCase());
            getFormation(xformation.getCodeFormation().toUpperCase());
        } catch (FormationNotFoundException e) {
            Formation formation = new FormationAdapter(xformation).toFormation();
            formation = formationRepository.save(formation);
            return new FormationAdapter(formation).toXFormation();
        }
        throw new FormationAlreadyExistsException();
    }

    public XFormation findFormationById(String id) {
        if (!checkIfCurrentUserHasRighttoAccess(id))
            throw new FormationAccessDeniedException();

        Formation form = getFormation(id);
        FormationAdapter formAdapter = new FormationAdapter(form);
        return formAdapter.toXFormation();

    }

    public String deleteFormationById(String id) {
        if (!authService.isAdm()) throw new FormationAccessDeniedException();// should throw AccessDeniedException

        Formation form = getFormation(id);
        if (!form.getPromotions().isEmpty()) // check if formation has promotion
            throw new FormationDeleteHasPromotionException(); // throw exception FormationDeleteHasPromotionException
        if (!form.getUniteEnseignements().isEmpty()) // check if formation has UE
            throw new FormationDeleteHasUEException(); // throw exception FormationDeleteHasUEException

        formationRepository.delete(form);
        return form.getId();
    }

    // ----------------- UE ----------------- //
    public List<XUniteEnseignement> findByUeByCodeFormation(String codeFormation) {
        if (!checkIfCurrentUserHasRighttoAccess(codeFormation))
            throw new FormationAccessDeniedException(); // throw exception AccessDeniedException

        return getFormation(codeFormation).getUniteEnseignements()
                .stream()
                .map(ue -> new UniteEnseignementAdapter(ue).toXUniteEnseignement())
                .toList();

    }

    public XUniteEnseignement createUE(XUniteEnseignement xUE) {
        if (!authService.isAdm()) throw new FormationAccessDeniedException(); // throw exception AccessDeniedException
        // check if formation exist
        // check if UE exist
        xUE.setCodeFormation(xUE.getCodeFormation().toUpperCase());
        xUE.setCodeUe(xUE.getCodeUe().toUpperCase());
        try {
            getEnseignant(xUE.getNoEnseignant());
            getUE(xUE.getCodeFormation(), xUE.getCodeUe());
        } catch (UENotFoundException e) {
            UniteEnseignement ue = new UniteEnseignementAdapter(xUE).toUniteEnseignement();
            ue = uniteEnseignementRepository.save(ue);
            return new UniteEnseignementAdapter(ue).toXUniteEnseignement();
        }
        throw new UEAlreadyExistsException();
    }

    public XUniteEnseignement findUE(String codeFormation, String codeUE) {
        if (!checkIfCurrentUserHasRighttoAccess(codeFormation)) throw new FormationAccessDeniedException();
        UniteEnseignement ue = getUE(codeFormation, codeUE); // this will throw exception if UE not found
        return new UniteEnseignementAdapter(ue).toXUniteEnseignement();

    }
    public XUniteEnseignement updateUE(String codeFormation, String codeUE, XUniteEnseignement xuniteEnseignement) {
        if (!authService.isAdm()) throw new FormationAccessDeniedException();
        xuniteEnseignement.setCodeUe(xuniteEnseignement.getCodeUe().toUpperCase());
        xuniteEnseignement.setCodeFormation(xuniteEnseignement.getCodeFormation().toUpperCase());
        if (!codeFormation.toUpperCase().equals(xuniteEnseignement.getCodeFormation()) || !codeUE.toUpperCase().equals(xuniteEnseignement.getCodeUe())) {
            throw new UENotMatchException();
        }

        getUE(codeFormation, codeUE); // this will throw exception if UE not found
        getEnseignant(xuniteEnseignement.getNoEnseignant());
        UniteEnseignement ueUpdated = new UniteEnseignementAdapter(xuniteEnseignement).toUniteEnseignement();
        ueUpdated = uniteEnseignementRepository.save(ueUpdated);
        return new UniteEnseignementAdapter(ueUpdated).toXUniteEnseignement();
    }

    public DeleteUE200Response deleteUE(String codeFormation, String codeUe) {
        if (!authService.isAdm()) throw new FormationAccessDeniedException();

        DeleteUE200Response response = new DeleteUE200Response();
        UniteEnseignement ue = getUE(codeFormation, codeUe); // this will throw exception if UE not found

        // verifier si l'UE n'a pas d'EC
        if (!ue.getElementConstitutifs().isEmpty()) throw new UEDeleteHasECException();
        // verifier si l'UE n'a pas d'evaluation
        if (!uniteEnseignementRepository.getEvalutionsOfUE(codeFormation, codeUe).isEmpty())  throw new UEDeleteHasEvaluationException();

        uniteEnseignementRepository.delete(ue);
        return response.codeUe(codeUe).codeFormation(codeFormation);
    }


    // ----------------- EC ----------------- //

    public ListECs getECsByFormAndUE(String codeFormation, String codeUe) {

        List<XElementConstitutif> xElementConstitutif;

        xElementConstitutif = elementConstitutifRepository
                .findById_CodeFormationAndId_CodeUe(codeFormation, codeUe)
                .stream()
                .map(ElementConstitutifAdapter::toXSElementConstitutif)
                .toList();

        return new ListECs().data(xElementConstitutif).total(xElementConstitutif.size());
    }

    public XElementConstitutif getECBack(String codeFormation, String codeUe, String codeEc) {

        ElementConstitutif ec = getEC(codeFormation, codeUe, codeEc);
        return new ElementConstitutifAdapter(ec).toXElementConstitutif();
    }







    // ----------------- FONCTIONS ----------------- //
    boolean checkIfCurrentUserHasRighttoAccess(String codeFormation) {
        String code_Formation = codeFormation.toUpperCase();
        getFormation(code_Formation); // this will throw exception if formation not found
        if (authService.isAdm()) return true;

        if (authService.isEns()) {
            Set<Formation> hasAccessToFormations = formationRepository.findByNoEnseignant(authService.getCurrentUser().getNoEnseignant());
            return hasAccessToFormations.stream().anyMatch(f -> f.getId().equals(code_Formation));
        }
        if (authService.isEtu()) {
            return authService.getCodeFormation().equals(code_Formation);
        }

        // any other role
        return false;

    }

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
        getUE(codeFormation, codeUe); // this will throw exception if UE not found
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