package backend.service;

import backend.auth.config.jLoginDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public jLoginDetails getCurrentUser() {
        return (jLoginDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isAdm() {
        return getCurrentUser().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADM"));
    }
    public boolean isEns() {
        return getCurrentUser().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ENS"));
    }
    public boolean isEtu() {
        return getCurrentUser().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ETU"));
    }
    public Integer getNoEnseignant() {
        return getCurrentUser().getNoEnseignant();
    }
    public String getAnneeUniversitaire() {
        return getCurrentUser().getAnneeUniversitaire();
    }
    public String getCodeFormation() {
        return getCurrentUser().getCodeFormation();
    }
    public String getNoEtudiant() {
        return getCurrentUser().getNoEtudiant();
    }


}
