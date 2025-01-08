package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.middleware.ContextUtil;
import es.rdajila.apppeliculas.model.Usuario;
import es.rdajila.apppeliculas.service.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class AuthController {
    private final IAuthService authService;
    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value={"/", "", "/login", "/login/"})
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal) {

        if (principal != null) {
            return "redirect:/peliculas";
        }

        if (error != null) {
            model.addAttribute("msg",
                    "Error al iniciar sesión: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = ContextUtil.getCurrentAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Usuario usuario = new Usuario();
        ResponseHelper _result = new ResponseHelper();
        model.addAttribute("usuario", model.getAttribute("usuario") != null ? (Usuario) model.getAttribute("usuario") : usuario);
        model.addAttribute("result", model.getAttribute("result") != null ? (ResponseHelper) model.getAttribute("result") : _result);
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(Model model, Usuario data, RedirectAttributes attributes) {
        ResponseHelper _result = authService.register(data);
        if (_result != null && _result.getStatus().compareTo(ConstantsHelper.SUCCESS)==0) {
            return "redirect:/login";
        }else{
            attributes.addFlashAttribute("usuario",data);
            attributes.addFlashAttribute("result",_result);
            return "redirect:/register";
        }
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }
}
