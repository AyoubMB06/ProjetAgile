package backend.auth.config;

import backend.entity.table.Authentification;
import backend.repository.table.AuthentificationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class jLoginService implements UserDetailsService {
    private AuthentificationRepository authentificationRepository;

    public jLoginService(AuthentificationRepository authentificationRepository) {
        this.authentificationRepository = authentificationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Authentification user = authentificationRepository.findByLoginConnectionLikeIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }

        return new jLoginDetails(user);
    }
}
