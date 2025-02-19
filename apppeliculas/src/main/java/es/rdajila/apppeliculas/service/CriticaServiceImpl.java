package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Critica;
import es.rdajila.apppeliculas.model.Usuario;
import lib.rdajila.helper.ResponseHelper;
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
public class CriticaServiceImpl implements ICriticaService{
    private final RestTemplate template;
    String url = "http://localhost:8090/api/criticas";

    @Autowired
    public CriticaServiceImpl(RestTemplate template) {
        this.template = template;
    }
    @Override
    public List<Critica> getAllByPeliculaId(Integer ePeliculaId) {
        try {
            ResponseEntity<Critica[]> response = template.exchange(url+"/pelicula/" + ePeliculaId,
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    Critica[].class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public ResponseHelper getMyCriticaByPeliculaId(Integer ePeliculaId) {
        try {
            ResponseEntity<ResponseHelper> response = template.exchange(url+"/mycritica/pelicula/" + ePeliculaId,
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    ResponseHelper.class);
            return response.getBody() != null ? response.getBody() : null;
        } catch(RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ResponseHelper save(Critica eCritica) {
        try {
            ResponseEntity<ResponseHelper> response = template.exchange(url,
                    HttpMethod.POST,
                    new HttpEntity<>(eCritica, null),
                    ResponseHelper.class);
            return response.getBody() != null ? response.getBody() : null;
        } catch(RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Integer eId) {
        template.delete(url + "/" + eId);
    }

    @Override
    public List<Critica> getAll() {
        try {
            ResponseEntity<Critica[]> response = template.exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    Critica[].class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Critica> getAllFilters(String eTxt, Integer ePeliculaId) {
        try {
            ResponseEntity<Critica[]> response = template.exchange(url + "?txt="+eTxt+"&peliculaId="+ePeliculaId,
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    Critica[].class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of();
    }
}
