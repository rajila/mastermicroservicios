package es.rdajila.apipeliculas.controller;

import es.rdajila.apipeliculas.dto.PeliculaDtoInput;
import es.rdajila.apipeliculas.model.Pelicula;
import es.rdajila.apipeliculas.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {
    private final IPeliculaService service;

    @Autowired
    public PeliculaController(IPeliculaService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Pelicula>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @CrossOrigin
    @PostMapping(value = {"", "/"})
    public ResponseEntity<String> create(@RequestBody PeliculaDtoInput ePeliculaInput) {
        Boolean _response = service.create(ePeliculaInput);
        if (_response) return ResponseEntity.ok("Pelicula registrada");
        return new ResponseEntity<>("Error!!", HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @PutMapping(value = {"", "/"})
    public ResponseEntity<String> update(@RequestBody PeliculaDtoInput ePeliculaInput) {
        Boolean _response = service.update(ePeliculaInput);
        if (_response) return ResponseEntity.ok("Pelicula actualizado");
        return new ResponseEntity<>("Error!!", HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @DeleteMapping("/{idPelicula}")
    public ResponseEntity<String> delete(@PathVariable("idPelicula") Integer eId) {
        Boolean _response = service.delete(eId);
        if (_response) return ResponseEntity.ok("Pelicula eliminado");
        return new ResponseEntity<>("Error!!", HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @GetMapping("/actor/{idActor}")
    public ResponseEntity<List<Pelicula>> getByActorId(@PathVariable("idActor") Integer eId) {
        return ResponseEntity.ok(service.getByActorId(eId));
    }

    @CrossOrigin
    @GetMapping( "/titulo/{eTitulo}")
    public ResponseEntity<List<Pelicula>> getByTitulo(@PathVariable("eTitulo") String eTitulo) {
        return ResponseEntity.ok(service.getByTitulo(eTitulo));
    }

    @CrossOrigin
    @GetMapping("/genero/{idGenero}")
    public ResponseEntity<List<Pelicula>> getByGeneroId(@PathVariable("idGenero") Integer eId) {
        return ResponseEntity.ok(service.getByGeneroId(eId));
    }
}
