package backend.repository.table;

import backend.entity.table.Evaluation;
import backend.entity.table.UniteEnseignement;
import backend.entity.table.UniteEnseignementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UniteEnseignementRepository extends JpaRepository<UniteEnseignement, UniteEnseignementId> {
    List<UniteEnseignement> findById_CodeFormation(String codeFormation);

    List<UniteEnseignement> findById_CodeFormationIn(Collection<String> codeFormations);

    UniteEnseignement findById_CodeFormationLikeIgnoreCaseAndId_CodeUeLikeIgnoreCase(String codeFormation, String codeUe);
    default UniteEnseignement findUE(String codeFormation, String codeUE){
        return findById_CodeFormationLikeIgnoreCaseAndId_CodeUeLikeIgnoreCase(codeFormation, codeUE);
    }

    @Query("select v from Evaluation v where v.promotion.codeFormation.id = ?1 and v.CodeUe = ?2")
    List<Evaluation> getEvalutionsOfUE(String codeFormation, String codeUe);

}