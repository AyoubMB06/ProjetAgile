package backend.repository.table;

import backend.entity.table.ElementConstitutif;
import backend.entity.table.Evaluation;
import backend.entity.table.Formation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElementConstitutifRepository extends JpaRepository<ElementConstitutif, String> {


    List<ElementConstitutif> findByUniteEnseignement_CodeFormation_IdLikeIgnoreCaseAndUniteEnseignement_Id_CodeUeLikeIgnoreCase(String id, String codeUe);
    default List<ElementConstitutif> findById_CodeFormationAndId_CodeUeByParam(String codeFormation, String codeUe, PageRequest of){
        return findByUniteEnseignement_CodeFormation_IdLikeIgnoreCaseAndUniteEnseignement_Id_CodeUeLikeIgnoreCase(codeFormation, codeUe);
    }
    default List<ElementConstitutif> findById_CodeFormationAndId_CodeUe(String codeFormation, String codeUe){
        return findByUniteEnseignement_CodeFormation_IdLikeIgnoreCaseAndUniteEnseignement_Id_CodeUeLikeIgnoreCase(codeFormation, codeUe);
    }

    List<ElementConstitutif> findByUniteEnseignement_CodeFormation_IdLikeIgnoreCase(String id);
    default List<ElementConstitutif> findById_CodeFormation(String id, PageRequest of){
        return findByUniteEnseignement_CodeFormation_IdLikeIgnoreCase(id);
    }

    List<ElementConstitutif> findByUniteEnseignement_Id_CodeUeLikeIgnoreCase(String codeUe);
    default List<ElementConstitutif> findById_CodeUe(String codeUe, PageRequest of){
        return findByUniteEnseignement_Id_CodeUeLikeIgnoreCase(codeUe);
    }

    @Query("select v from Evaluation v where v.promotion.codeFormation.id = ?1 and v.CodeUe = ?2 and v.CodeEc = ?3")
    List<Evaluation> getEvaluationsOfEC(String codeFormation, String codeUe, String codeEc);


    ElementConstitutif findByUniteEnseignement_Id_CodeFormationLikeIgnoreCaseAndUniteEnseignement_Id_CodeUeLikeIgnoreCaseAndCodeEcLikeIgnoreCase(String codeFormation, String codeUe, String codeEc);
    default ElementConstitutif findByPk(String codeFormation, String codeUe, String codeEc){
        return findByUniteEnseignement_Id_CodeFormationLikeIgnoreCaseAndUniteEnseignement_Id_CodeUeLikeIgnoreCaseAndCodeEcLikeIgnoreCase(codeFormation, codeUe, codeEc);
    };
}