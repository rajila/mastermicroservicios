package es.rdajila.apppeliculas.middleware;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtil {
    public static UserCurrent getCurrentUser() {
        return ContextUtil.getCurrentAuthentication() != null &&
                ContextUtil.getCurrentAuthentication().getPrincipal() != null &&
                ContextUtil.getCurrentAuthentication().getPrincipal() instanceof UserCurrent ?
                (UserCurrent) ContextUtil.getCurrentAuthentication().getPrincipal(): null;
    }

    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
