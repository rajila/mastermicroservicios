package es.rdajila.apipeliculas.middleware;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtil {
    public static UserLogin getCurrentUser() {
        return (UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static HttpHeaders getHeaders() {
        UserLogin userLogin = ContextUtil.getCurrentUser();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + userLogin.getToken());
        return headers;
    }
}
