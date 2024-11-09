package es.rdajila.cursos.controller;

import es.rdajila.cursos.model.Curso;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CursosController {
    private final List<Curso> listCursos;

    public CursosController() {
        listCursos = new ArrayList<>();
        listCursos.add(new Curso("Algoritmo", 20, "Carlos Jordan", 150));
    }

    @GetMapping("/cursos")
    public List<Curso> getAllCursos(){
        return listCursos;
    }

    @GetMapping("/cursos/{eName}")
    public List<Curso> getCursosByName(@PathVariable("eName") String eName){
        List<Curso> result = new ArrayList<>();
        for (Curso curso : listCursos) {
            if(curso.getNombre().contains(eName)){
                result.add(curso);
            }
        }
        return result;
    }

    @DeleteMapping("/cursos/{eName}")
    public void deleteCurso(@PathVariable("eName") String eName){
        listCursos.removeIf(curso -> curso.getNombre().compareTo(eName) == 0);
    }

    @PostMapping("/cursos")
    public Curso addCurso(@RequestBody Curso curso){
        listCursos.add(curso);
        return curso;
    }

    @PutMapping("/cursos")
    public Curso updateCurso(@RequestBody Curso curso){
        int index = listCursos.indexOf(curso);
        if (index != -1) listCursos.set(index, curso);
        return curso;
    }
}
