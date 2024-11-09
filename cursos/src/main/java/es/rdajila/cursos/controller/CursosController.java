package es.rdajila.cursos.controller;

import es.rdajila.cursos.model.Curso;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CursosController {
    private List<Curso> listCursos;

    public CursosController() {
        listCursos = new ArrayList<>();
        listCursos.add(new Curso("Algoritmo", 20, "Carlos Jordan", 150));
    }

    @GetMapping("/cursos")
    public List<Curso> getCursos(){
        return listCursos;
    }
}
