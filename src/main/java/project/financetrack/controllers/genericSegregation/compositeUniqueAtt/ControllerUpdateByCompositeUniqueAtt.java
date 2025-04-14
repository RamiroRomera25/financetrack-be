package project.financetrack.controllers.genericSegregation.compositeUniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceUpdateByCompositeUniqueAtt;

import java.util.Map;

public interface ControllerUpdateByCompositeUniqueAtt<E, I, M, DTOPUT, SERVICE extends ServiceUpdateByCompositeUniqueAtt<E, I, M, DTOPUT>> {

    SERVICE getService();

    @PutMapping("/unique")
    default ResponseEntity<M> getByCompositeUniqueAtt(@RequestBody DTOPUT dtoPut,
                                                      @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(getService().update(dtoPut, fields));
    }
}
