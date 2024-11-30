package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.dto.PeliculaFiltroIn;
import es.rdajila.apppeliculas.model.Actor;
import es.rdajila.apppeliculas.model.Genero;
import es.rdajila.apppeliculas.model.Pelicula;
import es.rdajila.apppeliculas.service.IActorService;
import es.rdajila.apppeliculas.service.IGeneroService;
import es.rdajila.apppeliculas.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {
    private final IPeliculaService peliculaService;
    private final IGeneroService generoService;
    private final IActorService actorService;

    @Autowired
    public PeliculaController(IPeliculaService peliculaService,
                              IGeneroService generoService,
                              IActorService actorService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
        this.actorService = actorService;
    }

    @GetMapping(value={"/", "", "/search", "/search/"})
    public String listadoCursos(Model model ) {
        List<Pelicula> listado = peliculaService.getAll();
        model.addAttribute("titulo", "Listado de todos los cursos");
        model.addAttribute("dataList", listado);

        List<Genero> generoList = generoService.getAll();
        List<Actor> actorList = actorService.getAll();
        model.addAttribute("generoList", generoList);
        model.addAttribute("actorList", actorList);

        PeliculaFiltroIn filtroCurrent = new PeliculaFiltroIn();
        filtroCurrent.setTitulo("");
        filtroCurrent.setAutorId(0);
        filtroCurrent.setAutorId(0);
        model.addAttribute("filtroCurrent", filtroCurrent);

        return "pelicula/index";
    }

    @GetMapping(value={"/search"}, params = {"titulo", "autorId", "generoId"})
    public String listadoCursosV2(Model model, @RequestParam String titulo, @RequestParam Integer autorId, @RequestParam Integer generoId) {
        List<Genero> generoList = generoService.getAll();
        List<Actor> actorList = actorService.getAll();
        model.addAttribute("generoList", generoList);
        model.addAttribute("actorList", actorList);

        PeliculaFiltroIn filtroCurrent = new PeliculaFiltroIn();
        filtroCurrent.setTitulo(titulo);
        filtroCurrent.setAutorId(autorId);
        filtroCurrent.setGeneroId(generoId);

        List<Pelicula> listado = peliculaService.getAllByFiltro(filtroCurrent);
        model.addAttribute("dataList", listado);
        model.addAttribute("filtroCurrent", filtroCurrent);
        return "pelicula/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer eId, RedirectAttributes attributes) {
        peliculaService.delete(eId);
        attributes.addFlashAttribute("msg", "La pelicula ha sido eliminada!");
        return "redirect:/peliculas";
    }
}
