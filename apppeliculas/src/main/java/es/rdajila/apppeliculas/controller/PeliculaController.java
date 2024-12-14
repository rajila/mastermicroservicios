package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.dto.PeliculaFiltroDtoIn;
import es.rdajila.apppeliculas.dto.PeliculaDtoIn;
import es.rdajila.apppeliculas.model.*;
import es.rdajila.apppeliculas.service.*;
import es.rdajila.apppeliculas.util.IUploadFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;

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
    private final IUploadFileService uploadFileService;


    @Autowired
    public PeliculaController(IPeliculaService peliculaService,
                              IGeneroService generoService,
                              IActorService actorService,
                              IDirectorService directorService,
                              IPaisService paisService,
                              ModelMapper modelMapper,
                              IUploadFileService uploadFileService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
        this.actorService = actorService;
        this.directorService = directorService;
        this.paisService = paisService;
        this.modelMapper = modelMapper;
        this.uploadFileService = uploadFileService;
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

        PeliculaFiltroDtoIn filtroCurrent = new PeliculaFiltroDtoIn();
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

        PeliculaFiltroDtoIn filtroCurrent = new PeliculaFiltroDtoIn();
        filtroCurrent.setTitulo(titulo);
        filtroCurrent.setAutorId(autorId);
        filtroCurrent.setGeneroId(generoId);

        List<Pelicula> listado = peliculaService.getAllByFiltro(filtroCurrent);
        model.addAttribute("dataList", listado);
        model.addAttribute("filtroCurrent", filtroCurrent);
        return "pelicula/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer eId, RedirectAttributes attributes) {
        peliculaService.delete(eId);
        attributes.addFlashAttribute("msg", "La pelicula ha sido eliminada!");
        return "redirect:/peliculas";
    }

    @GetMapping(value={"/nuevo", "/nuevo/"})
    public String create(Model model) {
        InitListados(model);

        PeliculaDtoIn dataCurrent = new PeliculaDtoIn();
        dataCurrent.setId(0);
        dataCurrent.setPortada("");
        model.addAttribute("dataCurrent", dataCurrent);
        model.addAttribute("titulo", "Nueva pelicula");

        return "pelicula/peliculaform";
    }

    @GetMapping(value={"/editar/{id}"})
    public String editar(Model model, @PathVariable("id") Integer eId) {
        InitListados(model);

        Pelicula _dataDb = peliculaService.getById(eId);
        PeliculaDtoIn dataCurrent;

        if (_dataDb != null) {
            dataCurrent =  modelMapper.map(_dataDb, PeliculaDtoIn.class);
            dataCurrent.init();
        } else dataCurrent = new PeliculaDtoIn();

        model.addAttribute("dataCurrent", dataCurrent);
        model.addAttribute("titulo", "Editar pelicula");

        return "pelicula/peliculaform";
    }

    @PostMapping("/save")
    public String save(PeliculaDtoIn data, @RequestParam(value="file", required=false) MultipartFile file, RedirectAttributes attributes) {
        if (!file.isEmpty()) {
            String b64 = uploadFileService.getBase64(file);
            data.setPortada(b64);
        } else if(data.getPortada().trim().isEmpty()) {
            try {
                Resource recurso = uploadFileService.load("portadaNo.jpg");
                String b64 = uploadFileService.getBase64(recurso.getFile());
                data.setPortada(b64);
            } catch(Exception ex) { ex.printStackTrace(); }
        }

        peliculaService.save(data);
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron guardados!");
        return "redirect:/peliculas";
    }

    private void InitListados(Model model) {
        List<Genero> generoList = generoService.getAll();
        List<Actor> actorList = actorService.getAll();
        List<Pais> paisList = paisService.getAll();
        List<Director> directorList = directorService.getAll();

        model.addAttribute("generoList", generoList);
        model.addAttribute("actorList", actorList);
        model.addAttribute("paisList", paisList);
        model.addAttribute("directorList", directorList);
    }
}
