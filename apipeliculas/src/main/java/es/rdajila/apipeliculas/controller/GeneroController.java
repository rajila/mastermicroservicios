package es.rdajila.apipeliculas.controller;

import es.rdajila.apipeliculas.model.Genero;
import es.rdajila.apipeliculas.service.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {
    private final IGeneroService service;

    @Autowired
    public GeneroController(IGeneroService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Genero>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
