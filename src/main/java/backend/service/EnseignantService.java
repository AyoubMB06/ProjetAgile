package backend.service;

import backend.adapter.EnseignantAdapter;
import backend.entity.table.Enseignant;
import backend.exceptions.definition.Enseignant.*;
import backend.repository.table.*;
import openAPI.model.OEnseignant;
import openAPI.model.PEnseignant;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnseignantService {
    private final EnseignantRepository enseignantRepository;
    public final AuthService authService;

    public EnseignantService(EnseignantRepository enseignantRepository,
                             AuthService authService) {

        this.enseignantRepository = enseignantRepository;
        this.authService = authService;
    }

    public List<OEnseignant> findEnseignants(Integer start, Integer size) {
        return enseignantRepository
                .findAll(PageRequest.of(start, size))
                .stream()
                .map(enseignant -> (OEnseignant)new EnseignantAdapter(enseignant).toPEnseignant())
                .toList();
    }

    public Integer countAll() {
        return enseignantRepository.countAll();
    }


    // ONLY ADM and ENS can access this method
    public OEnseignant findEnseignant(Integer id) {
        // RÈGLES: vous devez décider de renvoyer PEnseignant ou GEnseignant
        /*
        si l'utilisateur connecté est un ENS:
            - et que l'identifiant n'est PAS le même que celui de l'utilisateur connecté, renvoyer GEnseignant de la même promotion.
            - sinon si l'identifiant est le même que celui de l'utilisateur connecté, renvoyer PEnseignant
        si l'utilisateur connecté est un ADM:
            - renvoyer PEnseignant
        */
        Enseignant ens = getEnseignantById(id);
        EnseignantAdapter ensAdapter = new EnseignantAdapter(ens);
        return ensAdapter.toPEnseignant();
    }

    // ONLY ADM can access this method
    public PEnseignant editEnseignant(Integer noEnseignant, PEnseignant pEns) {
        if(pEns.getNoEnseignant() == null) pEns.setNoEnseignant(noEnseignant);
        if (pEns.getNoEnseignant().intValue() != noEnseignant) throw new EnseignantEditException();
        Enseignant ens = getEnseignantById(noEnseignant);
        Enseignant newEns = new EnseignantAdapter(pEns).toEnseignant();
        newEns = enseignantRepository.save(newEns);
        return new EnseignantAdapter(newEns).toPEnseignant();
    }

    // ONLY ADM can access this method
    public PEnseignant createEnseignant(PEnseignant pEns) {
        // check if the enseignant already exists (email perso, ubo)
       // if (enseignantRepository.findById(pEns.getNoEnseignant()).isPresent()) throw new EnseignantAlreadyExistsException();
        Enseignant ens = new EnseignantAdapter(pEns).toEnseignant();
        ens = enseignantRepository.save(ens);
        return new EnseignantAdapter(ens).toPEnseignant();
    }

    // ONLY ADM can access this method
    public int deleteEnseignant(Integer noEnseignant) {
        Enseignant ens = getEnseignantById(noEnseignant);
        // check if the enseignant is not responsable of any EC/UE/Promo
        if (!ens.getPromotions().isEmpty()) throw new EnseignantDeleteHasPromotionException();
        if (!ens.getUniteEnseignements().isEmpty()) throw new EnseignantDeleteHasUEException();
        if (!ens.getElementConstitutifs().isEmpty()) throw new EnseignantDeleteHasECException();
        enseignantRepository.delete(ens);
        return noEnseignant;
    }

    /* functions to help with the implementation of the rules */
    private boolean isSamePromotion(Integer IdCurrentEns, Integer idEns) {
        // Meme promotion veut dire que l'enseignant recherché est:
        // - responsable de la promotion de l'enseignant connecté
        // - responsable d'une UE de la promotion de l'enseignant connecté
        // - responsable d'un EC de la promotion de l'enseignant connecté
        Enseignant ens = enseignantRepository.findById(IdCurrentEns).orElse(null);
        // - responsable de la promotion de l'enseignant connecté
        boolean ensPromos = new HashSet<>(ens
                .getPromotions()
                .stream()
                .map(promo -> promo.getNoEnseignant().getId())
                .collect(Collectors.toSet()))
                .contains(idEns);

        if(ensPromos) return true;

        // - responsable d'une UE de la promotion de l'enseignant connecté
        boolean ensUEs = new HashSet<>(ens
                .getUniteEnseignements()
                .stream()
                .map(ue -> ue.getNoEnseignant().getId())
                .collect(Collectors.toSet()))
                .contains(idEns);
        if(ensUEs) return true;

        // - responsable d'un EC de la promotion de l'enseignant connecté
        Enseignant xens = getEnseignantById(idEns);
        Set<String> currentFormations = ens
                .getElementConstitutifs()
                .stream()
                .map(ec -> ec.getUniteEnseignement().getCodeFormation().getId())
                .collect(Collectors.toSet());

        Set<String> targetFormations = xens
                .getElementConstitutifs()
                .stream()
                .map(ec -> ec.getUniteEnseignement().getCodeFormation().getId())
                .collect(Collectors.toSet());

        return currentFormations.stream().anyMatch(targetFormations::contains);

    }


    public Enseignant getEnseignantById(Integer noEnseignant) {
        Enseignant ens = enseignantRepository.findById(noEnseignant).orElse(null);
        if (ens == null)  throw new EnseignantNotFoundException(); // need to throw exception
        return ens;
    }
}
