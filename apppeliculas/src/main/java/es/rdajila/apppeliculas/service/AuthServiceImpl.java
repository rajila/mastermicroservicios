package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.LoginDtoIn;
import es.rdajila.apppeliculas.dto.LoginDtoOut;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements IAuthService {
    private final RestTemplate template;
    private final String url = "http://localhost:8090/api/auth";

    public AuthServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public LoginDtoOut login(LoginDtoIn eData) {
        ResponseEntity<LoginDtoOut> response = template.exchange(url + "/login",
                HttpMethod.POST,
                new HttpEntity<LoginDtoIn>(eData, null),
                LoginDtoOut.class);
        return response.getBody() != null ? response.getBody() : null;
    }
}
