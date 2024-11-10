package es.rdajila.cursosjpa.controller;

import es.rdajila.cursosjpa.model.Curso;
import es.rdajila.cursosjpa.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CursosController {
    @Autowired
    ICursoService cursoService;

    @GetMapping("/cursos")
    public List<Curso> getAll() {
        return cursoService.getAll();
    }

    @GetMapping("/cursos/{eId}")
    public Curso getById(@PathVariable("eId") Long eId) {
        return cursoService.getById(eId);
    }

    @GetMapping("/cursos/nombre/{eNombre}")
    public List<Curso> getByName(@PathVariable("eNombre") String eNombre) {
        return cursoService.getByName(eNombre);
    }

    @GetMapping("/cursos/categoria/{eCategoria}")
    public List<Curso> getByCategory(@PathVariable("eCategoria") String eCategoria) {
        return cursoService.getByCategory(eCategoria);
    }

    @GetMapping("/cursos/profesor/{eProfesor}")
    public List<Curso> getByTeacher(@PathVariable("eProfesor") String eProfesor) {
        return cursoService.getByTeacher(eProfesor);
    }

    @PostMapping(value = "/cursos", produces = MediaType.TEXT_PLAIN_VALUE)
    public String save(@RequestBody Curso eCourse) {
        return String.valueOf(cursoService.save(eCourse));
    }

    @PutMapping("/cursos")
    public void update(@RequestBody Curso eCourse) {
        cursoService.update(eCourse);
    }

    @DeleteMapping(value = "/cursos/{eId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarCurso(@PathVariable("eId") Long eId) {
        return String.valueOf(cursoService.delete(eId));
    }
}
