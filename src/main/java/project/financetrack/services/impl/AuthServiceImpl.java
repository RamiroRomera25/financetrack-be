package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.financetrack.dtos.user.UserDTO;
import project.financetrack.dtos.user.UserDTOPost;
import project.financetrack.dtos.user.login.LoginRequest;
import project.financetrack.dtos.user.login.TokenResponse;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.entities.UserEntity;
import project.financetrack.repositories.UserRepository;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.AuthService;
import project.financetrack.services.EmailService;
import project.financetrack.services.ProjectService;
import project.financetrack.services.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final ProjectService projectService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public UserDTO register(UserDTOPost post) {

        if (userService.getOptionalByUniqueField("email", post.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use.");
        }

        UserEntity user = new UserEntity(post);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(post.getPassword()));
        user.setPremium(false);

        UserDTO userDTO = new UserDTO(userRepository.save(user));

        emailService.welcomeMail(userDTO);

        return userDTO;
    }

    public TokenResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserEntity user = userService.getOptionalByUniqueField("email", request.getEmail())
                .orElseThrow();
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new TokenResponse(accessToken, refreshToken, new UserDTO(user));
    }

    @Override
    public Boolean getEmailExists(String email) {
        return userService.getOptionalByUniqueField("email", email).isEmpty();
    }

    @Override
    public boolean canAccessProject(Long userId, Long projectId) {
         for (ProjectEntity project : this.projectService.getAllByUniqueFields("user.id", userId)) {
             if (project.getId().equals(projectId)) {
                 return true;
             }
         }

        return false;
    }
}
