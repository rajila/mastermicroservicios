package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImpl implements IPaisService{
    private final RestTemplate template;

    String url = "http://localhost:8090/api/paises";

    @Autowired
    public PaisServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Pais> getAll() {
        try {
            ResponseEntity<Pais[]> response = template.exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    Pais[].class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of();
    }
}
