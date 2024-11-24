package es.rdajila.apipeliculas.controller;

import es.rdajila.apipeliculas.dto.ActorDtoInput;
import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actores")
public class ActorController {
    private final IActorService service;

    @Autowired
    public ActorController(IActorService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Actor>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @CrossOrigin
    @PostMapping(value = {"", "/"})
    public ResponseEntity<String> create(@RequestBody ActorDtoInput eActorInput) {
        Boolean _response = service.create(eActorInput);
        if (_response) return ResponseEntity.ok("Actor registrado");
        return new ResponseEntity<>("Error!!", HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @PutMapping(value = {"", "/"})
    public ResponseEntity<String> update(@RequestBody ActorDtoInput eActorInput) {
        Boolean _response = service.update(eActorInput);
        if (_response) return ResponseEntity.ok("Actor actualizado");
        return new ResponseEntity<>("Error!!", HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @DeleteMapping("/{idActor}")
    public ResponseEntity<String> delete(@PathVariable("idActor") Integer eId) {
        Boolean _response = service.delete(eId);
        if (_response) return ResponseEntity.ok("Actor eliminado");
        return new ResponseEntity<>("Error!!", HttpStatus.FAILED_DEPENDENCY);
    }
}
