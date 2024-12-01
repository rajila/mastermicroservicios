package es.rdajila.apipeliculas.controller;

import es.rdajila.apipeliculas.model.Director;
import es.rdajila.apipeliculas.service.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/directores")
public class DirectorController {
    private final IDirectorService service;

    @Autowired
    public DirectorController(IDirectorService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Director>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
