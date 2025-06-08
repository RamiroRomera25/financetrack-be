package project.financetrack.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.dtos.common.ErrorApi;
import project.financetrack.enums.MaturityState;
import project.financetrack.services.EmailService;
import project.financetrack.services.MaturityService;
import project.financetrack.services.ReminderService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class PingController {

    private final EmailService emailService;

    private final MaturityService maturityService;

    private final ReminderService reminderService;

    /**
     * The health check method.
     * @return the word pong
     */
    @Operation(
            summary = "Check healthy of the app",
            description = "If the app it's alive response pong")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/notificated/{id}")
    public void asd(@PathVariable Long id) {
        emailService.maturityEmail(maturityService.getById(id), MaturityState.NOTIFICATED);
    }

    @PostMapping("/late/{id}")
    public void add(@PathVariable Long id) {
        emailService.maturityEmail(maturityService.getById(id), MaturityState.LATE);
    }

    @PostMapping("/reminder/{id}")
    public void dd(@PathVariable Long id) {
        emailService.reminderEmail(reminderService.getById(id));
    }
}
