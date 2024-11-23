package es.rdajila.clientcourses.controller;

import es.rdajila.clientcourses.model.Curso;
import es.rdajila.clientcourses.service.ICursoService;
import es.rdajila.clientcourses.util.PaginatorRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    ICursoService cursosService;

    @GetMapping(value = {"/", "/home", ""})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/listado")
    public String listadoCursos(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado = cursosService.getAll(pageable);
        PaginatorRender<Curso> pageRender = new PaginatorRender<Curso>("/cursos/listado", listado);
        model.addAttribute("titulo", "Listado de todos los cursos");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo curso");
        Curso curso = new Curso();
        model.addAttribute("curso", curso);
        return "cursos/formCurso";
    }

    @GetMapping("/buscar")
    public String buscar(Model model) {
        return "cursos/searchCurso";
    }

    @GetMapping("/idcurso/{id}")
    public String buscarCursoPorId(Model model, @PathVariable("id") Integer id) {
        Curso curso = cursosService.getById(id);
        model.addAttribute("curso", curso);
        return "cursos/formCurso";
    }

    @GetMapping("/nombre")
    public String buscarCursosPorNombre(Model model, @RequestParam(name="page",
            defaultValue="0") int page, @RequestParam("nombre") String nombre) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado;
        if (nombre.equals("")) {
            listado = cursosService.getAll(pageable);
        } else {
            listado = cursosService.getByName(nombre, pageable);
        }
        PaginatorRender<Curso> pageRender = new PaginatorRender<Curso>("/listado", listado);
        model.addAttribute("titulo", "Listado de cursos por nombre");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/categoria")
    public String buscarCursosPorCategoria(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("categoria") String categoria) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado = cursosService.getByCategory(categoria, pageable);
        PaginatorRender<Curso> pageRender = new PaginatorRender<Curso>("/listado", listado);
        model.addAttribute("titulo", "Listado de cursos por categoria");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @GetMapping("/profesor")
    public String buscarCursosPorProfesor(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam("profesor") String profesor) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Curso> listado;
        if (profesor.equals("")) {
            listado = cursosService.getAll(pageable);
        } else {
            listado = cursosService.getByTeacher(profesor, pageable);
        }
        PaginatorRender<Curso> pageRender = new PaginatorRender<Curso>("/listado", listado);
        model.addAttribute("titulo", "Listado de cursos por profesor");
        model.addAttribute("listadoCursos", listado);
        model.addAttribute("page", pageRender);
        return "cursos/listCurso";
    }

    @PostMapping("/guardar/")
    public String guardarCurso(Model model, Curso curso, RedirectAttributes attributes) {
//    	if(curso != null) {	System.out.println(curso.getNombre()); }
        cursosService.save(curso);
        model.addAttribute("titulo", "Nuevo curso");
        attributes.addFlashAttribute("msg", "Los datos del curso fueron guardados!");
        return "redirect:/cursos/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(Model model, @PathVariable("id") Integer id) {
        Curso curso = cursosService.getById(id);
        model.addAttribute("titulo", "Editar curso");
        model.addAttribute("curso", curso);
        return "cursos/formCurso";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarCurso(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        cursosService.delete(id);
        attributes.addFlashAttribute("msg", "Los datos del curso fueron borrados!");
        return "redirect:/cursos/listado";
    }
}
