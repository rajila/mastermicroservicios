package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.dto.PeliculaFiltroDtoIn;
import es.rdajila.apppeliculas.dto.PeliculaDtoIn;
import es.rdajila.apppeliculas.dto.ReviewDtoIn;
import es.rdajila.apppeliculas.model.*;
import es.rdajila.apppeliculas.service.*;
import es.rdajila.apppeliculas.util.IUploadFileService;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ResponseHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;

import java.text.DecimalFormat;
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
    private final ICriticaService criticaService;


    @Autowired
    public PeliculaController(IPeliculaService peliculaService,
                              IGeneroService generoService,
                              IActorService actorService,
                              IDirectorService directorService,
                              IPaisService paisService,
                              ModelMapper modelMapper,
                              IUploadFileService uploadFileService,
                              ICriticaService criticaService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
        this.actorService = actorService;
        this.directorService = directorService;
        this.paisService = paisService;
        this.modelMapper = modelMapper;
        this.uploadFileService = uploadFileService;
        this.criticaService = criticaService;
    }

    @GetMapping(value={"/", "", "/search", "/search/"})
    public String allOrSearch(Model model ) {
        List<Pelicula> listado = peliculaService.getAll();
        listado.forEach(pelicula -> {
            List<Critica> lCriticas = criticaService.getAllByPeliculaId(pelicula.getId());
            // Calculo de nota
            if(!lCriticas.isEmpty()) {
                double notaFinal = lCriticas.stream().mapToInt(Critica::getNota).sum() / (lCriticas.size() * 1.0);
                pelicula.setNota(notaFinal);
                pelicula.setNotaEval((int)notaFinal);
            } else {
                pelicula.setNota(0);
                pelicula.setNotaEval(0);
            }
        });
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer eId, RedirectAttributes attributes) {
        peliculaService.delete(eId);
        attributes.addFlashAttribute("msg", "La pelicula ha sido eliminada!");
        return "redirect:/peliculas";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
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

    @GetMapping(value={"/{id}/reviews"})
    public String reviews(Model model, @PathVariable("id") Integer eId) {
        Pelicula _dataDb = peliculaService.getById(eId);
        PeliculaDtoIn dataCurrent;
        // Buscar si ya emiti un comentario
        ResponseHelper miCritica = criticaService.getMyCriticaByPeliculaId(_dataDb.getId());
        if (miCritica != null)
            model.addAttribute("criticaId", miCritica.getIdData());
        else model.addAttribute("criticaId", 0);

        // Obtener todos las criticas de la pelicula
        List<Critica> lCriticas = criticaService.getAllByPeliculaId(_dataDb.getId());
        model.addAttribute("lCriticas", lCriticas);

        if (_dataDb != null) {
            dataCurrent =  modelMapper.map(_dataDb, PeliculaDtoIn.class);
            dataCurrent.init();
        } else dataCurrent = new PeliculaDtoIn();

        // Calculo de nota
        if(!lCriticas.isEmpty()) {
            double notaFinal = lCriticas.stream().mapToInt(Critica::getNota).sum() / (lCriticas.size() * 1.0);
            dataCurrent.setNota(notaFinal);
        } else dataCurrent.setNota(0);

        ReviewDtoIn reviewDtoIn = model.getAttribute("dataReview") != null ? (ReviewDtoIn) model.getAttribute("dataReview"):null;
        if (reviewDtoIn == null) {
            reviewDtoIn = new ReviewDtoIn();
            reviewDtoIn.setPeliculaId(eId);
            reviewDtoIn.setPeliculaId(eId);
        }

        ResponseHelper _result = new ResponseHelper();
        model.addAttribute("result", model.getAttribute("result") != null ? (ResponseHelper) model.getAttribute("result") : _result);

        model.addAttribute("dataNota", new DecimalFormat("#.#").format(dataCurrent.getNota()));
        model.addAttribute("dataNotaVal", (int)dataCurrent.getNota());
        model.addAttribute("dataCurrent", reviewDtoIn);
        model.addAttribute("data", dataCurrent);
        model.addAttribute("titulo", "Reviews pelicula");

        return "pelicula/reviews";
    }

    @PostMapping(value={"/{id}/reviews"})
    public String reviewsSave(@PathVariable("id") Integer eId, ReviewDtoIn data, RedirectAttributes attributes) {
        Critica _critica = modelMapper.map(data, Critica.class);
        ResponseHelper _result = criticaService.save(_critica);
        if ( !(_result != null && _result.getStatus().compareTo(ConstantsHelper.SUCCESS)==0) ) {
            attributes.addFlashAttribute("dataReview",data);
            attributes.addFlashAttribute("result",_result);
        }
        return "redirect:/peliculas/" + eId + "/reviews";
    }
}
