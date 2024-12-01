package es.rdajila.apipeliculas.controller;

import es.rdajila.apipeliculas.model.Pais;
import es.rdajila.apipeliculas.service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {
    private final IPaisService service;

    @Autowired
    public PaisController(IPaisService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Pais>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
