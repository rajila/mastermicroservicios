package es.rdajila.apiusuarioscriticas.controller;

import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import es.rdajila.apiusuarioscriticas.service.IAuthService;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/signup")
public class AuthController {
    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin
    @PostMapping({"", "/"})
    public ResponseEntity<ResponseHelper> register(@RequestBody UsuarioDtoIn eUsuarioDtoIn) {
        ResponseHelper _response = authService.create(eUsuarioDtoIn);
        if (_response != null) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
    }
}
