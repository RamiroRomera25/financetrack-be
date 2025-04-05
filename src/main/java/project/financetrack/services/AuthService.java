package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.user.UserDTO;
import project.financetrack.dtos.user.UserDTOPost;
import project.financetrack.dtos.user.login.ChangePasswordRequest;
import project.financetrack.dtos.user.login.LoginRequest;
import project.financetrack.dtos.user.login.TokenResponse;

@Service
public interface AuthService {
    UserDTO register(UserDTOPost request);

    TokenResponse authenticate(LoginRequest request);

    Boolean getEmailExists(String email);
}
