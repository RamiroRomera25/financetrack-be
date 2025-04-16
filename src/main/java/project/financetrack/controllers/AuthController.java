package project.financetrack.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.dtos.user.UserDTO;
import project.financetrack.dtos.user.UserDTOPost;
import project.financetrack.dtos.user.login.LoginRequest;
import project.financetrack.dtos.user.login.TokenResponse;
import project.financetrack.services.AuthService;
import project.financetrack.services.EmailService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTOPost request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest request) {
        TokenResponse token = authService.authenticate(request);

        return ResponseEntity.ok(token);
    }

    @GetMapping(value = "/validEmail")
    public ResponseEntity<Boolean> validEmail(@RequestParam("email") String email) {
        Boolean emailExists = this.authService.getEmailExists(email);
        return ResponseEntity.ok(emailExists);
    }
}

