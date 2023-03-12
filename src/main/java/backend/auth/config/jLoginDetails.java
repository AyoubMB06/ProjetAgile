package backend.auth.config;

import backend.entity.table.Authentification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class jLoginDetails implements UserDetails {
    private Authentification user;

    public jLoginDetails(Authentification user) {
        this.user = user;
    }

    public Integer getNoEnseignant() {
        return user.getNoEnseignant().getId();
    }

    public String getNoEtudiant() {
        return user.getNoEtudiant().getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    public String getAnneeUniversitaire() {
        return user.getNoEtudiant().getPromotion().getAnneeUniversitaire();
    }

    public String getCodeFormation() {
        return user.getNoEtudiant().getPromotion().getCodeFormation().getId();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginConnection();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
