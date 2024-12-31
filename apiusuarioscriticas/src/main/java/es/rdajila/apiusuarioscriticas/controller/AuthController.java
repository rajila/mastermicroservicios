package es.rdajila.apiusuarioscriticas.controller;

import es.rdajila.apiusuarioscriticas.dto.LoginDtoIn;
import es.rdajila.apiusuarioscriticas.dto.LoginDtoOut;
import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import es.rdajila.apiusuarioscriticas.jwt.GeneratorJwt;
import es.rdajila.apiusuarioscriticas.service.IAuthService;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final IAuthService authService;
    private final GeneratorJwt generatorJwt;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IAuthService authService,
                          GeneratorJwt generatorJwt) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.generatorJwt = generatorJwt;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<LoginDtoOut> login(@RequestBody LoginDtoIn loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generatorJwt.generateToken(authentication);
        return new ResponseEntity<>(new LoginDtoOut(token, ""), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<ResponseHelper> register(@RequestBody UsuarioDtoIn eUsuarioDtoIn) {
        ResponseHelper _response = authService.create(eUsuarioDtoIn);
        if (_response != null) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
    }
}
