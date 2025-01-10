package es.rdajila.apppeliculas.service;

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
public class UsuarioServiceImpl implements IUsuarioService{
    private final RestTemplate template;
    String url = "http://localhost:8090/api/usuarios";

    @Autowired
    public UsuarioServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Usuario> getAll() {
        try {
            ResponseEntity<Usuario[]> response = template.exchange(url+"/by/roles",
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    Usuario[].class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public void delete(Integer eId) {
        template.delete(url + "/" + eId);
    }

    @Override
    public Usuario getById(Integer eId) {
        return template.getForObject(url + "/" + eId, Usuario.class);
    }

    @Override
    public ResponseHelper save(Usuario eUsuario) {
        try {
            ResponseEntity<ResponseHelper> response = template.exchange(url,
                    eUsuario.getId() != null && eUsuario.getId() > 0 ? HttpMethod.PUT : HttpMethod.POST,
                    new HttpEntity<>(eUsuario, null),
                    ResponseHelper.class);
            return response.getBody() != null ? response.getBody() : null;
        } catch(RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> getAllFilters(String eTxt, Integer eRolId) {
        try {
            ResponseEntity<Usuario[]> response = template.exchange(url + "/by/roles?txt="+eTxt+"&rolId="+eRolId,
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    Usuario[].class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of();
    }
}
