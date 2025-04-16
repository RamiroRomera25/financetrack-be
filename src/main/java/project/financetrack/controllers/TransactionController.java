package project.financetrack.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.dtos.maturity.MaturityDTOPost;
import project.financetrack.dtos.maturity.MaturityDTOPut;
import project.financetrack.dtos.transaction.TransactionDTOPost;
import project.financetrack.dtos.transaction.TransactionDTOPut;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.entities.TransactionEntity;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.AuthService;
import project.financetrack.services.TransactionService;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController
{
    private final TransactionService transactionService;

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping("/project/{projectId}")
    public ResponseEntity<TransactionEntity> create(@RequestBody @Valid TransactionDTOPost dtoPost, Authentication auth, @PathVariable Long projectId) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(transactionService.create(dtoPost));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/project/{projectId}")
    public List<TransactionEntity> getAllGoalsByProjectId(@PathVariable Long projectId, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return transactionService.getAllByUniqueFields("project.id", projectId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<TransactionEntity> delete(@PathVariable Long projectId, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(transactionService.delete(projectId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/project/{projectId}")
    public ResponseEntity<TransactionEntity> update(@PathVariable Long projectId, @RequestBody @Valid TransactionDTOPut dtoPut, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(transactionService.update(dtoPut, projectId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
