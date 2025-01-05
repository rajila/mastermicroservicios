package es.uah.rdajila.apigateway.controller;

import es.uah.rdajila.apigateway.dto.LoginDtoIn;
import es.uah.rdajila.apigateway.dto.LoginDtoOut;
import es.uah.rdajila.apigateway.jwt.GeneratorJwt;
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
    private final GeneratorJwt generatorJwt;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          GeneratorJwt generatorJwt) {
        this.authenticationManager = authenticationManager;
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
}
