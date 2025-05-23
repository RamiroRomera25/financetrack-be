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
import project.financetrack.dtos.investment.InvestmentDTO;
import project.financetrack.dtos.investment.InvestmentDTOPost;
import project.financetrack.dtos.investment.InvestmentDTOPut;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.AuthService;
import project.financetrack.services.InvestmentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/investments")
public class InvestmentController
{

    private final InvestmentService investmentService;

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<InvestmentDTO> create(@RequestBody @Valid InvestmentDTOPost dtoPost, Authentication auth) throws IOException {
        if (authService.canAccessProject(jwtService.extractId(auth), dtoPost.getProjectId())) {
            return ResponseEntity.ok(investmentService.create(dtoPost));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/project/{projectId}")
    public List<InvestmentDTO> getAllInvestmentsByProjectId(@PathVariable Long projectId, Authentication auth) throws IOException {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return investmentService.getAllModelByUniqueFields("project.id", projectId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/project/{projectId}/{investmentId}")
    public ResponseEntity<InvestmentDTO> delete(@PathVariable Long projectId,
                                                   @PathVariable Long investmentId,
                                                   Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(investmentService.delete(investmentId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/project/{projectId}/{investmentId}")
    public ResponseEntity<InvestmentDTO> update(@PathVariable Long projectId,
                                                   @PathVariable Long investmentId,
                                                   @RequestBody @Valid InvestmentDTOPut dtoPut,
                                                   Authentication auth) throws IOException {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(investmentService.update(dtoPut, investmentId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
