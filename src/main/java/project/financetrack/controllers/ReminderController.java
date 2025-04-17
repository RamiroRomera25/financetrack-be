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
import project.financetrack.dtos.reminder.ReminderDTOPost;
import project.financetrack.dtos.reminder.ReminderDTOPut;
import project.financetrack.entities.ReminderEntity;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.AuthService;
import project.financetrack.services.ReminderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reminders")
public class ReminderController
{
    private final ReminderService reminderService;

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<ReminderEntity> create(@RequestBody @Valid ReminderDTOPost dtoPost,
                                                 Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), dtoPost.getProjectId())) {
            return ResponseEntity.ok(reminderService.create(dtoPost));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/project/{projectId}")
    public List<ReminderEntity> getAllRemindersByProjectId(@PathVariable Long projectId,
                                                            Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return reminderService.getAllByUniqueFields("project.id", projectId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/project/{projectId}/{reminderId}")
    public ResponseEntity<ReminderEntity> delete(@PathVariable Long projectId,
                                                 @PathVariable Long reminderId,
                                                 Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(reminderService.delete(reminderId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/project/{projectId}/{reminderId}")
    public ResponseEntity<ReminderEntity> update(@PathVariable Long projectId,
                                                 @PathVariable Long reminderId,
                                                 @RequestBody @Valid ReminderDTOPut dtoPut,
                                                 Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(reminderService.update(dtoPut, reminderId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
    
}
