package project.financetrack.controllers;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.MercadoPagoService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mp")
public class MercadoPagoController {

    private final MercadoPagoService mercadoPagoService;

    private final JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<Preference> createPreference(Authentication auth) throws MPException, MPApiException {
        return ResponseEntity.ok(mercadoPagoService.createPreference(jwtService.extractId(auth)));
    }

    @PostMapping("/notification/{userId}")
    public ResponseEntity<?> receiveNotification(@RequestBody Map<String, Object> body,
                                                 @PathVariable Long userId)
                                                throws MPException, MPApiException {
        String topic = (String) body.get("topic");
        String resource = (String) body.get("resource");

        return ResponseEntity.ok(mercadoPagoService.receiveNotification(topic, resource, userId));
    }
}
