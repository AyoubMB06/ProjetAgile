package backend.repository.table;

import backend.entity.table.Etudiant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    default Etudiant findByNoEtudiant(String s){
        return findById(s).get();
    }
    @Query("SELECT e FROM Etudiant e WHERE e.promotion.id.codeFormation = ?1 AND e.promotion.id.anneeUniversitaire = ?2")
    List<Etudiant> findEtudiantsByPromotion(String codeFormation, String anneeUniversitaire, PageRequest pageRequest);
    @Query("SELECT count(*) FROM Etudiant e WHERE e.promotion.id.codeFormation = ?1 AND e.promotion.id.anneeUniversitaire = ?2")
    Integer countEtudiantsByPromo(String codeFormation, String anneeUniversitaire);

    Etudiant findByPromotion_Id_CodeFormationLikeIgnoreCaseAndPromotion_Id_AnneeUniversitaireLikeIgnoreCase(String codeFormation, String anneeUniversitaire);

    default Etudiant findByPK(String codeFormation, String anneuni){
        return findByPromotion_Id_CodeFormationLikeIgnoreCaseAndPromotion_Id_AnneeUniversitaireLikeIgnoreCase(codeFormation, anneuni);
    }
}