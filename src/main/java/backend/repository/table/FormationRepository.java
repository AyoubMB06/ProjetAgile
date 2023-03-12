package backend.repository.table;

import backend.entity.table.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FormationRepository extends JpaRepository<Formation, String> {


    Formation findByIdLikeIgnoreCase(String id);
    @Query("SELECT f FROM Formation f " +
            "WHERE f.id IN (SELECT ue.id.codeFormation FROM UniteEnseignement ue WHERE ue.noEnseignant.id = ?1)" +
            "OR f.id IN (SELECT ec.uniteEnseignement.codeFormation.id FROM ElementConstitutif ec WHERE ec.noEnseignant.id = ?1) " +
            "OR f.id IN (SELECT p.id.codeFormation FROM Promotion p WHERE p.noEnseignant.id = ?1)"
    )
    Set<Formation> findByNoEnseignant(Integer noEnseignant);

}