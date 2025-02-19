package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.dto.CriticaFiltroDtoIn;
import es.rdajila.apppeliculas.model.Critica;
import es.rdajila.apppeliculas.model.Pelicula;
import es.rdajila.apppeliculas.service.ICriticaService;
import es.rdajila.apppeliculas.service.IPeliculaService;
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
@RequestMapping("/criticas")
public class CriticaController {
    private final ICriticaService criticaService;
    private final IPeliculaService peliculaService;

    @Autowired
    public CriticaController(ICriticaService criticaService, IPeliculaService peliculaService) {
        this.criticaService = criticaService;
        this.peliculaService = peliculaService;
    }

    @GetMapping(value={"/", ""})
    public String all(Model model) {
        boolean execFiltro = model.getAttribute("filtro") != null;
        String txtFilter = "";
        Integer peliculaId = 0;
        CriticaFiltroDtoIn filtro = (CriticaFiltroDtoIn) model.getAttribute("filtro");
        if (filtro != null) {
            txtFilter = filtro.getTxt() != null && !filtro.getTxt().isEmpty() ? filtro.getTxt() : "";
            peliculaId = filtro.getPeliculaId();
        }

        List<Critica> listado = execFiltro ?  criticaService.getAllFilters(txtFilter, peliculaId): criticaService.getAll();
        listado.forEach(el -> {
            // Obtener nombre de pelicula
            Pelicula pelicula = peliculaService.getById(el.getPeliculaId());
            if(pelicula != null) {
                el.setPelicula(pelicula.getTitulo());
            } else {
                el.setPelicula("NA");
            }
        });

        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("lCriticas", listado);

        List<Pelicula> peliculaList = peliculaService.getAll();
        model.addAttribute("peliculaList", peliculaList);

        CriticaFiltroDtoIn filtroCurrent = new CriticaFiltroDtoIn("",0);
        model.addAttribute("filtroCurrent", execFiltro ? filtro : filtroCurrent);

        return "critica/index";
    }

    @PostMapping("/search")
    public String search(Model model, CriticaFiltroDtoIn filtroCurrent, RedirectAttributes attributes) {
        attributes.addFlashAttribute("filtro", filtroCurrent);
        return "redirect:/criticas";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer eId, RedirectAttributes attributes) {
        criticaService.delete(eId);
        attributes.addFlashAttribute("msg", "La critica ha sido eliminada!");
        return "redirect:/criticas";
    }
}
