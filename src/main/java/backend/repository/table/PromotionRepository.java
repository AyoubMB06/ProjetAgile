package backend.repository.table;

import backend.entity.table.Promotion;
import backend.entity.table.PromotionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, PromotionId> {
    List<Promotion> findById_CodeFormationIn(Collection<String> codeFormations);

    // find prop by codeFOrmation and AnneeUniversitaire ignorecase
    List<Promotion> findById_CodeFormationIgnoreCaseAndId_AnneeUniversitaireIgnoreCase(String codeFormation, String anneeUniversitaire);


    default List<Promotion> findPromotionById(String codeFormation, String anneeUniversitaire){
        return findById_CodeFormationIgnoreCaseAndId_AnneeUniversitaireIgnoreCase(codeFormation, anneeUniversitaire);
    }

    Promotion findById_CodeFormationLikeIgnoreCaseAndId_AnneeUniversitaireLikeIgnoreCase(String codeFormation, String anneeUniversitaire);
    default Promotion findByPK(String codeFormation, String anneuni){
        return findById_CodeFormationLikeIgnoreCaseAndId_AnneeUniversitaireLikeIgnoreCase(codeFormation, anneuni);
    }



}