package es.rdajila.apppeliculas.controller;


import es.rdajila.apppeliculas.dto.UsuarioFiltroDtoIn;
import es.rdajila.apppeliculas.model.Rol;
import es.rdajila.apppeliculas.model.Usuario;
import es.rdajila.apppeliculas.service.IRolService;
import es.rdajila.apppeliculas.service.IUsuarioService;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ResponseHelper;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    private final IUsuarioService usuarioService;
    private final IRolService rolService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, IRolService rolService, ModelMapper modelMapper) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value={"/", ""})
    public String all(Model model) {
        boolean execFiltro = model.getAttribute("filtro") != null;
        String txtFilter = "";
        Integer rolId = 0;
        UsuarioFiltroDtoIn filtro = (UsuarioFiltroDtoIn) model.getAttribute("filtro");
        if (filtro != null) {
            txtFilter = filtro.getTxt();
            rolId = filtro.getRolId();
        }
        List<Usuario> listado = execFiltro ? usuarioService.getAllFilters(txtFilter, rolId): usuarioService.getAll();
        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("dataList", listado);

        List<Rol> rolList = rolService.getAll().stream().filter(el -> el.getCodigo().compareToIgnoreCase("admin") == 0 ||
                el.getCodigo().compareToIgnoreCase("user") == 0).toList();
        model.addAttribute("rolList", rolList);

        UsuarioFiltroDtoIn filtroCurrent = new UsuarioFiltroDtoIn("",0);
        model.addAttribute("filtroCurrent", execFiltro ? (UsuarioFiltroDtoIn) model.getAttribute("filtro") : filtroCurrent);

        return "usuario/index";
    }

    @PostMapping("/search")
    public String search(Model model, UsuarioFiltroDtoIn filtroCurrent, RedirectAttributes attributes) {
        attributes.addFlashAttribute("filtro", filtroCurrent);
        return "redirect:/usuarios";
    }

    @GetMapping(value={"/nuevo", "/nuevo/"})
    public String create(Model model) {
        List<Rol> rolList = rolService.getAll().stream().filter(el -> el.getCodigo().compareToIgnoreCase("admin") == 0 ||
                el.getCodigo().compareToIgnoreCase("user") == 0).toList();
        ResponseHelper _result = new ResponseHelper();

        model.addAttribute("rolList", rolList);
        model.addAttribute("result", model.getAttribute("result") != null ? (ResponseHelper) model.getAttribute("result") : _result);

        Usuario dataCurrent = new Usuario();
        dataCurrent.setId(0);
        model.addAttribute("dataCurrent", model.getAttribute("usuario") != null ? (Usuario) model.getAttribute("usuario") : dataCurrent);
        model.addAttribute("titulo", "Nuevo usuario");

        return "usuario/form";
    }

    @GetMapping(value={"/editar/{id}"})
    public String editar(Model model, @PathVariable("id") Integer eId) {
        List<Rol> rolList = rolService.getAll().stream().filter(el -> el.getCodigo().compareToIgnoreCase("admin") == 0 ||
                el.getCodigo().compareToIgnoreCase("user") == 0).toList();
        ResponseHelper _result = new ResponseHelper();

        model.addAttribute("rolList", rolList);
        model.addAttribute("result", model.getAttribute("result") != null ? (ResponseHelper) model.getAttribute("result") : _result);

        Usuario dataCurrent = usuarioService.getById(eId);
        model.addAttribute("dataCurrent", model.getAttribute("usuario") != null ? (Usuario) model.getAttribute("usuario") : dataCurrent);
        model.addAttribute("titulo", "Editar usuario");

        return "usuario/form";
    }

    @PostMapping("/save")
    public String save(Model model, Usuario data, RedirectAttributes attributes) {
        data.setBase64("test");
        data.setTipoDocumento(ConstantsHelper.DOCUMENT_GEN_TYPE);
        data.setTipoOrigenDocumento(ConstantsHelper.SOURCE_USUARIO_TYPE);
        attributes.addFlashAttribute("msg", "Los datos del usuario fueron guardados!");
        ResponseHelper _result = usuarioService.save(data);
        if (_result != null && _result.getStatus().compareTo(ConstantsHelper.SUCCESS)==0) {
            return "redirect:/usuarios";
        }else{
            attributes.addFlashAttribute("usuario",data);
            attributes.addFlashAttribute("result",_result);
            return data.getId() != null && data.getId() > 0 ? "redirect:/usuarios/editar/" + data.getId() : "redirect:/usuarios/nuevo";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer eId, RedirectAttributes attributes) {
        usuarioService.delete(eId);
        attributes.addFlashAttribute("msg", "El usuario ha sido eliminada!");
        return "redirect:/usuarios";
    }
}
