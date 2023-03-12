package backend.repository.table;

import backend.entity.table.Candidat;
import backend.entity.table.PromotionId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CandidatRepository extends JpaRepository<Candidat, String> {
    //default Candidat findByNoCandidat(String s){return findById(s).get();}
    @Query("SELECT c FROM Candidat c WHERE c.promotion.id.codeFormation= ?1 AND c.promotion.id.anneeUniversitaire = ?2")
    List<Candidat> findCandidatsByPromotion(String codeFormation, String anneeUniversitaire, PageRequest of);

    @Query("SELECT count(*) FROM Candidat c WHERE c.promotion.id.codeFormation = ?1 AND c.promotion.id.anneeUniversitaire = ?2")
    Integer countCandidatsByPromo(String codeFormation, String anneeUniversitaire);

    Candidat findByPromotion_Id_CodeFormationLikeIgnoreCaseAndPromotion_Id_AnneeUniversitaireLikeIgnoreCase(String codeFormation, String anneeUniversitaire);

    default Candidat findByPK(String codeFormation, String anneuni){
        return findByPromotion_Id_CodeFormationLikeIgnoreCaseAndPromotion_Id_AnneeUniversitaireLikeIgnoreCase(codeFormation, anneuni);
    }
}