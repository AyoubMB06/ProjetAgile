package backend.repository.table;

import backend.entity.table.Enseignant;
import backend.entity.table.ElementConstitutif;
import backend.entity.table.Etudiant;

import backend.entity.table.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {
    @Query("SELECT COUNT(*) FROM Enseignant")
    Integer countAll();

    @Query(
            // query to get all promotions in which enseignant is teaching any EC
            "select p from Promotion p " +
                    "where p.codeFormation.id in " +
                    "(select ec.uniteEnseignement.codeFormation.id from ElementConstitutif ec where ec.noEnseignant.id =  ?1)" +
                    "and p.id.anneeUniversitaire in (select e.promotion.id.anneeUniversitaire from Etudiant e where e.promotion.id.anneeUniversitaire = p.id.anneeUniversitaire and p.codeFormation.id = e.promotion.codeFormation.id)")

    List<Promotion> getPromotions(Integer noEnseignant);




}