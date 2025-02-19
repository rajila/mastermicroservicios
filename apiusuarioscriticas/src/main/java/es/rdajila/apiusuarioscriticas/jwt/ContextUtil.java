package es.rdajila.apiusuarioscriticas.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class ContextUtil {
    public static User getCurrentUser() {
        return ContextUtil.getCurrentAuthentication() != null &&
                ContextUtil.getCurrentAuthentication().getPrincipal() != null &&
                ContextUtil.getCurrentAuthentication().getPrincipal() instanceof User ?
                (User) ContextUtil.getCurrentAuthentication().getPrincipal(): null;
    }

    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUsername() {
        return getCurrentUser() != null ? getCurrentUser().getUsername(): "";
    }
}
