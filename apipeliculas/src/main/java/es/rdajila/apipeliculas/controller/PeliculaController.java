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
}
