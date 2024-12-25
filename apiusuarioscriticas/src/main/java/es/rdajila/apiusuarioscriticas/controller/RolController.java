package es.rdajila.apiusuarioscriticas.controller;

import es.rdajila.apiusuarioscriticas.service.IRolService;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final IRolService rolService;

    @Autowired
    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    @CrossOrigin
    @GetMapping({"/codigo/{eCode}"})
    public ResponseEntity<ResponseHelper> getById(@PathVariable("eCode") String eCode) {
        ResponseHelper _result = rolService.getByCode(eCode);
        return (_result == null) ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(_result);
    }
}
