package backend.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AUTHENTIFICATION")
public class Authentification {
    @Id
    @Column(name = "ID_CONNECTION", nullable = false)
    private Long id;

    @Column(name = "ROLE", nullable = false, length = 5)
    private String role;

    @Column(name = "LOGIN_CONNECTION", nullable = false, length = 64)
    private String loginConnection;

    @Column(name = "PSEUDO_CONNECTION", length = 240)
    private String pseudoConnection;

    @Column(name = "MOT_PASSE", length = 32)
    private String motPasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ETUDIANT")
    private Etudiant noEtudiant;


    public String getPassword() {
        return motPasse;
    }


    public String getUsername() {
        return loginConnection;
    }


    public String getEmail() {
        return loginConnection;

    }

    public void setEmail(String usr) {
        this.loginConnection = usr;

    }
}
