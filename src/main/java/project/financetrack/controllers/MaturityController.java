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
import project.financetrack.dtos.investment.InvestmentDTOPost;
import project.financetrack.dtos.investment.InvestmentDTOPut;
import project.financetrack.dtos.maturity.MaturityDTOPost;
import project.financetrack.dtos.maturity.MaturityDTOPut;
import project.financetrack.entities.InvestmentEntity;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.AuthService;
import project.financetrack.services.MaturityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/maturities")
public class MaturityController
{

    private final MaturityService maturityService;

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<MaturityEntity> create(@RequestBody @Valid MaturityDTOPost dtoPost, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), dtoPost.getProjectId())) {
            return ResponseEntity.ok(maturityService.create(dtoPost));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/project/{projectId}")
    public List<MaturityEntity> getAllMaturitiesByProjectId(@PathVariable Long projectId, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return maturityService.getAllByUniqueFields("project.id", projectId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/project/{projectId}/{maturityId}")
    public ResponseEntity<MaturityEntity> delete(@PathVariable Long projectId,
                                                 @PathVariable Long maturityId,
                                                 Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(maturityService.delete(maturityId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/project/{projectId}/{maturityId}")
    public ResponseEntity<MaturityEntity> update(@PathVariable Long projectId,
                                                 @PathVariable Long maturityId,
                                                 @RequestBody @Valid MaturityDTOPut dtoPut,
                                                 Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(maturityService.update(dtoPut, maturityId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
