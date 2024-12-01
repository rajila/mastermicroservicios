package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.dto.PeliculaFiltroIn;
import es.rdajila.apppeliculas.dto.PeliculaIn;
import es.rdajila.apppeliculas.model.*;
import es.rdajila.apppeliculas.service.*;
import org.modelmapper.ModelMapper;
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
    private final IPaisService paisService;
    private final IDirectorService directorService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeliculaController(IPeliculaService peliculaService,
                              IGeneroService generoService,
                              IActorService actorService,
                              IDirectorService directorService,
                              IPaisService paisService,
                              ModelMapper modelMapper) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
        this.actorService = actorService;
        this.directorService = directorService;
        this.paisService = paisService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value={"/", "", "/search", "/search/"})
    public String allOrSearch(Model model ) {
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
    public String search(Model model, @RequestParam String titulo, @RequestParam Integer autorId, @RequestParam Integer generoId) {
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

    @GetMapping(value={"/nuevo", "/nuevo/"})
    public String create(Model model) {
        List<Genero> generoList = generoService.getAll();
        List<Actor> actorList = actorService.getAll();
        List<Pais> paisList = paisService.getAll();
        List<Director> directorList = directorService.getAll();

        model.addAttribute("generoList", generoList);
        model.addAttribute("actorList", actorList);
        model.addAttribute("paisList", paisList);
        model.addAttribute("directorList", directorList);

        PeliculaIn dataCurrent = new PeliculaIn();
        dataCurrent.setId(0);
        model.addAttribute("dataCurrent", dataCurrent);
        model.addAttribute("titulo", "Nueva pelicula");

        return "pelicula/peliculaform";
    }

    @GetMapping(value={"/editar/{id}"})
    public String editar(Model model, @PathVariable("id") Integer eId) {
        List<Genero> generoList = generoService.getAll();
        List<Actor> actorList = actorService.getAll();
        List<Pais> paisList = paisService.getAll();
        List<Director> directorList = directorService.getAll();

        model.addAttribute("generoList", generoList);
        model.addAttribute("actorList", actorList);
        model.addAttribute("paisList", paisList);
        model.addAttribute("directorList", directorList);

        Pelicula _dataDb = peliculaService.getById(eId);
        PeliculaIn dataCurrent = null;

        if (_dataDb != null) {
            dataCurrent =  modelMapper.map(_dataDb, PeliculaIn.class);
            dataCurrent.init();
        } else dataCurrent = new PeliculaIn();

        model.addAttribute("dataCurrent", dataCurrent);
        model.addAttribute("titulo", "Editar pelicula");

        return "pelicula/peliculaform";
    }

    @PostMapping("/save")
    public String save(Model model, PeliculaIn data, RedirectAttributes attributes) {
        peliculaService.save(data);
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron guardados!");
        return "redirect:/peliculas";
    }
}
