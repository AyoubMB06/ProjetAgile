package backend.repository.table;

import backend.entity.table.Etudiant;
import backend.entity.table.ReponseEvaluation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReponseEvaluationRepository extends JpaRepository<ReponseEvaluation, Long> {
    @Query("SELECT count(*) FROM ReponseEvaluation re WHERE re.noEtudiant.id = ?1")
    Integer CountReponseEvaluationOfEtudiant(String noEtudiant);

}
