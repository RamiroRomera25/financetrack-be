package project.financetrack.security.UserDetails;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import project.financetrack.services.UserService;

/**
 * Implementación de la interfaz UserDetailsService,
 * contiene la lógica de autenticación.
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new CustomUserDetails(userService.getModelByUniqueField("email", username));
    }
}
