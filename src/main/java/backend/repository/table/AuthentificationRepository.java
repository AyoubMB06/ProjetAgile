package backend.repository.table;

import backend.entity.table.Authentification;
import backend.entity.table.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthentificationRepository extends JpaRepository<Authentification, Long> {
    //  Optional<Authentification> findByEmail(@NonNull String loginConnection);
    Authentification findByLoginConnectionLikeIgnoreCase(String loginConnection);
    void deleteByNoEtudiant(Etudiant noEtudiant);


}