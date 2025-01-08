package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.dto.ActorDtoIn;
import es.rdajila.apppeliculas.model.*;
import es.rdajila.apppeliculas.service.IActorService;
import es.rdajila.apppeliculas.service.IPaisService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/actores")
public class ActorController {
    private final IActorService actorService;
    private final IPaisService paisService;
    private final ModelMapper modelMapper;

    @Autowired
    public ActorController(IActorService actorService, IPaisService paisService, ModelMapper modelMapper) {
        this.actorService = actorService;
        this.paisService = paisService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value={"/", ""})
    public String all(Model model ) {
        List<Actor> listado = actorService.getAll();
        model.addAttribute("titulo", "Listado de todos los cursos");
        model.addAttribute("dataList", listado);

        return "actor/index";
    }

    @GetMapping(value={"/nuevo", "/nuevo/"})
    public String create(Model model) {
        List<Pais> paisList = paisService.getAll();

        model.addAttribute("paisList", paisList);

        ActorDtoIn dataCurrent = new ActorDtoIn();
        dataCurrent.setId(0);
        model.addAttribute("dataCurrent", dataCurrent);
        model.addAttribute("titulo", "Nuevo actor");

        return "actor/actorform";
    }

    @GetMapping(value={"/editar/{id}"})
    public String editar(Model model, @PathVariable("id") Integer eId) {
        List<Pais> paisList = paisService.getAll();
        model.addAttribute("paisList", paisList);


        Actor _dataDb = actorService.getById(eId);
        ActorDtoIn dataCurrent = null;

        if (_dataDb != null) {
            dataCurrent =  modelMapper.map(_dataDb, ActorDtoIn.class);
            dataCurrent.setNombre(_dataDb.getNombre());
            dataCurrent.setApellido(_dataDb.getApellido());
            dataCurrent.setIdPais(_dataDb.getCountry().getId());
        } else dataCurrent = new ActorDtoIn();

        model.addAttribute("dataCurrent", dataCurrent);
        model.addAttribute("titulo", "Editar actor");

        return "actor/actorform";
    }

    @PostMapping("/save")
    public String save(Model model, ActorDtoIn data, RedirectAttributes attributes) {
        actorService.save(data);
        attributes.addFlashAttribute("msg", "Los datos del actor fueron guardados!");
        return "redirect:/actores";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer eId, RedirectAttributes attributes) {
        actorService.delete(eId);
        attributes.addFlashAttribute("msg", "El actor ha sido eliminada!");
        return "redirect:/actores";
    }

}
