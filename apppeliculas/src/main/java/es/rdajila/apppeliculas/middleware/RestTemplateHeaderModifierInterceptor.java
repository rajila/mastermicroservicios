package es.rdajila.apppeliculas.middleware;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = null;
        UserCurrent userLogin = ContextUtil.getCurrentUser();
        if (userLogin != null) {
            // Agregamos el token a todas las peticiones que se invoquen desde RestTemplate
            request.getHeaders().add("Authorization", "Bearer " + userLogin.getToken());
            request.getHeaders().add("roluser", userLogin.getRol());
            response = execution.execute(request, body);
        } else {
            response = execution.execute(request, body);
        }
        return response;
    }
}
