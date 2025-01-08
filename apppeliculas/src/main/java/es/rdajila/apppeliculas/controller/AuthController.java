package es.rdajila.apppeliculas.controller;

import es.rdajila.apppeliculas.middleware.ContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AuthController {
    @GetMapping(value={"/", "", "/login"})
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal) {

        if (principal != null) {
            return "redirect:/actores";
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
}
