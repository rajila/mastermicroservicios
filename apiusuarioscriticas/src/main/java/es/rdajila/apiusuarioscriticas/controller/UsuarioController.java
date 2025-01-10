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
import java.util.Optional;

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

    @GetMapping({"/by/roles/", "/by/roles"})
    public ResponseEntity<List<Usuario>> getAllByRoles() {
        return ResponseEntity.ok(service.getAllFilterRolesAdminOrUser());
    }

    @GetMapping(value={"/by/roles"}, params = {"txt", "rolId"})
    public ResponseEntity<List<Usuario>> getAllByFilters(@RequestParam("txt") Optional<String> txt, @RequestParam("rolId") Optional<Integer> rolId) {
        String filterTxt = txt.orElse("");
        Integer filterRolId = rolId.orElse(0);
        return ResponseEntity.ok(service.getByNombresOrCorreoOrRolId(filterTxt, filterRolId));
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