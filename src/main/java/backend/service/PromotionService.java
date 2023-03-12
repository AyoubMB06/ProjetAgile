package backend.service;

import backend.adapter.CandidatAdapter;
import backend.adapter.EtudiantAdapter;
import backend.adapter.PromotionAdapter;
import backend.entity.table.*;
import backend.exceptions.definition.Candidat.CandidatAlreadyExistsException;
import backend.exceptions.definition.Candidat.CandidatNotFoundException;
import backend.exceptions.definition.Candidat.CandidatPromotionMismatchException;
import backend.exceptions.definition.Enseignant.EnseignantNotFoundException;
import backend.exceptions.definition.Etudiant.*;
import backend.exceptions.definition.Formation.FormationNotFoundException;
import backend.exceptions.definition.Promotion.*;
import backend.repository.table.*;
import openAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    AuthService authService;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private ReponseEvaluationRepository reponseEvaluationRepository;
    @Autowired
    private  AuthentificationRepository authentificationRepository;

    public ListPromotions findPromotions(Integer page, Integer size) {
        List<XPromotion> xPromotions;
        // for ens
        if(authService.isEns())
        {
            xPromotions = enseignantRepository.getPromotions(authService.getNoEnseignant()).stream().map(PromotionAdapter::toXPromotion).toList();
            if(xPromotions.size() == 0) throw new PromotionNotFoundException();

            return new ListPromotions().data(xPromotions).total(xPromotions.size());
        }
        // for student, need to be tested
        if(authService.isEtu()){
            xPromotions = promotionRepository.findPromotionById(authService.getCodeFormation(), authService.getAnneeUniversitaire())
                    .stream()
                    .map(PromotionAdapter::toXPromotion).toList();
            if(xPromotions.size() == 0) throw new PromotionAccessDeniedException();
            return new ListPromotions().data(xPromotions).total(xPromotions.size());
        }

        // cas d'un admin (if there is one or any other role)
        // use authorize to prevent access to this method
        xPromotions = promotionRepository.findAll(PageRequest.of(page, size))
                .map(PromotionAdapter::toXPromotion).toList();
        if(xPromotions.size() == 0) throw new PromotionNotFoundException();
        return new ListPromotions().data(xPromotions).total(xPromotions.size());
    }
    public ListEtudiants findEtudiants(String codeFormation, String anneeUniversitaire,Integer page, Integer size) {
        ListEtudiants letu = new ListEtudiants();
        Promotion promo= promotionRepository.findPromotionById(codeFormation, anneeUniversitaire)
                .stream()
                .findFirst()
                .orElse(null);
        if(promo == null) throw new PromotionNotFoundException();
        if(authService.isAdm() ) {
            letu.setData(etudiantRepository.findEtudiantsByPromotion(codeFormation, anneeUniversitaire, PageRequest.of(page, size))
                    .stream()
                    .map(etudiant -> (OEtudiant) new EtudiantAdapter(etudiant).toPEtudiant())
                    .toList());
            letu.setTotal(etudiantRepository.countEtudiantsByPromo(codeFormation, anneeUniversitaire));
            return letu;
        }
        if(authService.isEns()){
            List<Promotion> promotions;
            promotions = enseignantRepository.getPromotions(authService.getNoEnseignant()).stream().toList();
            if(promotions.stream()
                    .anyMatch(promotion ->
                            promotion.getCodeFormation().getId().equals(codeFormation) &&
                                    promotion.getAnneeUniversitaire().equals(anneeUniversitaire))) {
                letu.setData(etudiantRepository.findEtudiantsByPromotion(codeFormation, anneeUniversitaire, PageRequest.of(page, size))
                        .stream()
                        .map(etudiant -> (OEtudiant) new EtudiantAdapter(etudiant).toGEtudiant())
                        .toList());
                letu.setTotal(etudiantRepository.countEtudiantsByPromo(codeFormation, anneeUniversitaire));
                return letu;
            }
        }
        throw new EtudiantAccessDeniedException();
    }
    public XPromotion findPromotion(String codeFormation, String anneeUniversitaire) {
        getFormation(codeFormation);
        Promotion promotion;
        if(authService.isAdm()){
            promotion = promotionRepository.findPromotionById(codeFormation, anneeUniversitaire)
                    .stream()
                    .findFirst()
                    .orElse(null);
            if(promotion == null) throw new PromotionNotFoundException();
            return PromotionAdapter.toXPromotion(promotion);
        }
        if(authService.isEns()){
            List<Promotion> Promotions;
            Promotions = enseignantRepository.getPromotions(authService.getNoEnseignant()).stream().toList();
            if(Promotions.stream()
                    .anyMatch(Promotion ->
                            Promotion.getCodeFormation().getId().equals(codeFormation) &&
                                    Promotion.getAnneeUniversitaire().equals(anneeUniversitaire))) {

                promotion = promotionRepository.findPromotionById(codeFormation, anneeUniversitaire)
                        .stream()
                        .findFirst()
                        .orElse(null);
                if(promotion == null) throw new PromotionNotFoundException();
                return PromotionAdapter.toXPromotion(promotion);
            }
            promotion = promotionRepository.findPromotionById(codeFormation, anneeUniversitaire)
                    .stream()
                    .findFirst()
                    .orElse(null);
            if(promotion == null) throw new PromotionNotFoundException();
        }
        throw new PromotionAccessDeniedException();
    }
    public OEtudiant findEtudiant(String codeFormation, String anneeUniversitaire, String noEtudiant) {
        Etudiant etu;
        if(authService.isAdm()) {
            etu = etudiantRepository.findById(noEtudiant).orElse(null);
            if(etu == null) throw new EtudiantNotFoundException();
            return new EtudiantAdapter(etu).toPEtudiant();}
        if(authService.isEns()){
            List<Promotion> Promotions;
            Promotions = enseignantRepository.getPromotions(authService.getNoEnseignant()).stream().toList();
            if(Promotions.stream()
                    .anyMatch(Promotion ->
                            Promotion.getCodeFormation().getId().equals(codeFormation) &&
                                    Promotion.getAnneeUniversitaire().equals(anneeUniversitaire))) {
                etu = etudiantRepository.findById(noEtudiant).orElse(null);
                if(etu == null) throw new EtudiantNotFoundException();
                return new EtudiantAdapter(etu).toGEtudiant();
            }
        }
        if(authService.isEtu()){
            etu = etudiantRepository.findById(authService.getNoEtudiant()).orElse(null);
            if(etu == null) throw new EtudiantNotFoundException();
            return new EtudiantAdapter(etu).toPEtudiant();
        }
        throw new EtudiantAccessDeniedException();
    }
    public DeleteEtudiant200Response deleteEtudiant(String codeFormation, String anneeUniversitaire, String noEtudiant) {
        getPromotion(codeFormation, anneeUniversitaire);
        Etudiant etu = getEtudiant(noEtudiant);
        if(reponseEvaluationRepository.CountReponseEvaluationOfEtudiant(noEtudiant) > 0) throw new EtudiantHasReponseEvaluationException();
        authentificationRepository.deleteByNoEtudiant(etu);
        etudiantRepository.delete(etu);
        DeleteEtudiant200Response response= new DeleteEtudiant200Response();
        response.setNoEtudiant(noEtudiant);
        return response;
    }

    public ListCandidats findCandidats(String codeFormation, String anneeUniversitaire, Integer page, Integer size) {
        getPromotion(codeFormation, anneeUniversitaire);
        if(promotionRepository.findPromotionById(codeFormation, anneeUniversitaire).isEmpty()) throw new PromotionNotFoundException();
        ListCandidats lcan = new ListCandidats();

        lcan.setData(candidatRepository.findCandidatsByPromotion(codeFormation, anneeUniversitaire, PageRequest.of(page, size))
                .stream()
                .map(candidat -> (OCandidat) new CandidatAdapter(candidat).toPCandidat())
                .toList());
        lcan.setTotal(candidatRepository.countCandidatsByPromo(codeFormation, anneeUniversitaire));
        return lcan;
    }
    public OCandidat findCandidat(String codeFormation, String anneeUniversitaire, String noCandidat) {
        getPromotion(codeFormation, anneeUniversitaire);
        Candidat can = candidatRepository.findById(noCandidat).orElse(null);
        if(can== null) throw new CandidatNotFoundException();
        return new CandidatAdapter(can).toPCandidat();
    }

    public DeleteCandidat200Response deleteCandidat(String codeFormation, String anneeUniversitaire, String noCandidat) {
        getPromotion(codeFormation, anneeUniversitaire);
        Candidat can = candidatRepository.findById(noCandidat).orElse(null);
        if(can== null) throw new CandidatNotFoundException();
        candidatRepository.delete(can);
        DeleteCandidat200Response response= new DeleteCandidat200Response();
        response.setNoCandidat(noCandidat);
        return response;
    }


    // karima

    // ONLY ADM can access this method
    public XPromotion createPromotion(XPromotion xpromotion) {
        xpromotion.setCodeFormation(xpromotion.getCodeFormation().toUpperCase());
        getFormation(xpromotion.getCodeFormation());
        getEnseignant(xpromotion.getNoEnseignant());
        try{
            getPromotion(xpromotion.getCodeFormation(),xpromotion.getAnneeUniversitaire());
        }catch (PromotionNotFoundException e){
            Promotion promotion = new PromotionAdapter(xpromotion).toPromotion();
            promotion = promotionRepository.save(promotion);
            return PromotionAdapter.toXPromotion(promotion);
        }

        throw new PromotionAlreadyExistsException();
    }

    // ONLY ADM can access this method
    public PCandidat createCandidat(String codeFormation, String anneeUniversitaire, PCandidat pcandidat) {
        getPromotion(codeFormation, anneeUniversitaire); // need to throw exception
        if(!pcandidat.getCodeFormation().toUpperCase().equals(codeFormation) || !pcandidat.getAnneeUniversitaire().equals(anneeUniversitaire))
            throw new CandidatPromotionMismatchException();


        // check if candidat already exists with his email
        try{
            getCandidat(pcandidat.getNoCandidat());
        }catch (CandidatNotFoundException e){
            Candidat can = new CandidatAdapter(pcandidat).toCandidat();
            can = candidatRepository.save(can);
            return new CandidatAdapter(can).toPCandidat();
        }
        throw new CandidatAlreadyExistsException();
    }

    // ONLY ADM can access this method
    public PEtudiant createEtudiant(String codeFormation, String anneeUniversitaire, PEtudiant petudiant) {
        getPromotion(codeFormation, anneeUniversitaire); // need to throw exception
        if(!petudiant.getCodeFormation().toUpperCase().equals(codeFormation) || !petudiant.getAnneeUniversitaire().equals(anneeUniversitaire))
            throw new EtudiantPromotionMismatchException();

          // check if etudiant already exists with his email
        try{
            getEtudiant(petudiant.getNoEtudiant());
        }catch (EtudiantNotFoundException e){
            Etudiant etu = new EtudiantAdapter(petudiant).toEtudiant();
            etu = etudiantRepository.save(etu);
            return new EtudiantAdapter(etu).toPEtudiant();
        }
        throw new EtudiantAlreadyExistsException();
    }

    // ONLY ADM can access this method
    public PCandidat editCandidat(String codeFormation, String anneeUniversitaire, String noCandidat, PCandidat pcandidat) {

        getPromotion(codeFormation, anneeUniversitaire); // need to throw exception
        getCandidat(noCandidat); // need to throw exception
        if(!pcandidat.getCodeFormation().toUpperCase().equals(codeFormation) || !pcandidat.getAnneeUniversitaire().equals(anneeUniversitaire))
            throw new CandidatPromotionMismatchException();

        Candidat newcand = new CandidatAdapter(pcandidat).toCandidat();
        newcand = candidatRepository.save(newcand);
        return new CandidatAdapter(newcand).toPCandidat();
    }

    // ONLY ADM can access this method
    public PEtudiant editEtudiant(String codeFormation, String anneeUniversitaire, String noEtudiant, PEtudiant petudiant) {
        petudiant.setCodeFormation(petudiant.getCodeFormation().toUpperCase());
        petudiant.setNoEtudiant(noEtudiant.toUpperCase());
        petudiant.setAnneeUniversitaire(petudiant.getAnneeUniversitaire().toUpperCase()); //
        codeFormation = codeFormation.toUpperCase();
        anneeUniversitaire = anneeUniversitaire.toUpperCase(); //

        if(!petudiant.getCodeFormation().toUpperCase().equals(codeFormation) || !petudiant.getAnneeUniversitaire().equals(anneeUniversitaire))
            throw new EtudiantPromotionMismatchException();

        getPromotion(codeFormation, anneeUniversitaire); // need to throw exception
        getEtudiant(noEtudiant); // need to throw exception

        Etudiant newetud= new EtudiantAdapter(petudiant).toEtudiant();
        newetud = etudiantRepository.save(newetud);
        return new EtudiantAdapter(newetud).toPEtudiant();
    }

    // ONLY ADM can access this method
    public PromotionId deletePromotion(String codeFormation, String anneeUniversitaire){
        Promotion promo = getPromotion(codeFormation, anneeUniversitaire); // need to throw exception

        Candidat candidat = candidatRepository.findByPK(codeFormation,anneeUniversitaire);
        if (candidat != null) throw new PromotionHasCandidatException(); // need to throw exception

        Etudiant etudiant = etudiantRepository.findByPK(codeFormation,anneeUniversitaire);
        if (etudiant != null) throw new PromotionHasStudentsException(); // need to throw exception


        promotionRepository.delete(promo);
        PromotionId promotionId = new PromotionId(promo.getId().getCodeFormation(), promo.getId().getAnneeUniversitaire());
        return promotionId;
    }

    public Formation getFormation(String codeFormation) {
        codeFormation = codeFormation.toUpperCase();
        Formation form = formationRepository.findByIdLikeIgnoreCase(codeFormation);
        if (form == null) throw new FormationNotFoundException();
        return form;
    }
    public Enseignant getEnseignant(Integer noEnseignant) {
        Enseignant enseignant = enseignantRepository.findById(noEnseignant).orElse(null);
        if (enseignant == null) throw new EnseignantNotFoundException();
        return enseignant;
    }

    public Promotion getPromotion(String codeFormation, String anneeUniversitaire) {
        codeFormation = codeFormation.toUpperCase();
        getFormation(codeFormation);
        Promotion promo = promotionRepository.findByPK(codeFormation,anneeUniversitaire);
        if (promo == null) throw new PromotionNotFoundException();
        return promo;
    }

    public Candidat getCandidat(String noCandidat){
        noCandidat = noCandidat.toUpperCase();
        Candidat candidat = candidatRepository.findById(noCandidat).orElse(null);
        if (candidat == null) throw new CandidatNotFoundException();
        return candidat;
    }

    public Etudiant getEtudiant(String noEtudiant){
        noEtudiant = noEtudiant.toUpperCase();
        Etudiant etudiant = etudiantRepository.findById(noEtudiant).orElse(null);
        if (etudiant == null) throw new EtudiantNotFoundException();
        return etudiant;
    }
}

