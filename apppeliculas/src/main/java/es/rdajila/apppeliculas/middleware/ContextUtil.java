package es.rdajila.apppeliculas.middleware;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtil {
    public static UserCurrent getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication() != null ?
                (UserCurrent) SecurityContextHolder.getContext().getAuthentication().getPrincipal(): null;
    }

    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
