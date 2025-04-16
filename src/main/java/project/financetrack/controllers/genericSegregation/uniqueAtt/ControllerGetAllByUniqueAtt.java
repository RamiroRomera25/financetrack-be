package project.financetrack.controllers.genericSegregation.uniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;

import java.util.List;

public interface ControllerGetAllByUniqueAtt<E, I, M, SERVICE extends ServiceGetAllByUniqueAtt<E, I, M>> {

    SERVICE getService();

    @GetMapping("/unique/{value}/list")
    default ResponseEntity<List<M>> getAllByCompositeUniqueAtt(@RequestParam(required = false, defaultValue = "id") String field,
                                                            @PathVariable Object value) {
        return ResponseEntity.ok(getService().getAllModelByUniqueFields(field, value));
    }
}
