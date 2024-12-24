package es.rdajila.apiusuarioscriticas.controller;

import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import es.rdajila.apiusuarioscriticas.model.Usuario;
import es.rdajila.apiusuarioscriticas.service.IUsuarioService;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final IUsuarioService  service;

    @Autowired
    public UsuarioController(IUsuarioService service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @CrossOrigin
    @GetMapping({"/{eId}"})
    public ResponseEntity<Usuario> getById(@PathVariable("eId") Integer eId) {
        Usuario _usuario = service.getById(eId);
        return (_usuario == null) ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(_usuario);
    }

    @CrossOrigin
    @PostMapping(value = {"", "/"})
    public ResponseEntity<ResponseHelper> create(@RequestBody UsuarioDtoIn eDataInput) {
        ResponseHelper _response = service.create(eDataInput);
        if (_response != null) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @PutMapping(value = {"", "/"})
    public ResponseEntity<ResponseHelper> update(@RequestBody UsuarioDtoIn eDataInput) {
        ResponseHelper _response = service.update(eDataInput);
        if (_response != null) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @DeleteMapping("/{eId}")
    public ResponseEntity<ResponseHelper> delete(@PathVariable("eId") Integer eId) {
        ResponseHelper _response = service.delete(eId);
        return ResponseEntity.ok(_response);
    }
}