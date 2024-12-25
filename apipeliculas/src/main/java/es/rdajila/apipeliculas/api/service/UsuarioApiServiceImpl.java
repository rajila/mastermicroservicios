package es.rdajila.apipeliculas.api.service;

import es.rdajila.apipeliculas.api.model.UsuarioApi;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioApiServiceImpl implements IUsuarioApiService {
    private final RestTemplate template;
    private final String url = "http://localhost:8082/api/usuarios";

    @Autowired
    public UsuarioApiServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public UsuarioApi getById(Integer eId) {
        return template.getForObject(url + "/" + eId, UsuarioApi.class);
    }

    @Override
    public ResponseHelper create(UsuarioApi usuarioApi) {
        return template.postForObject(url, usuarioApi, ResponseHelper.class);
    }

    @Override
    public ResponseHelper update(UsuarioApi usuarioApi) {
        ResponseEntity<ResponseHelper> response = template.execute(url,
                HttpMethod.PUT,
                template.httpEntityCallback(usuarioApi),
                template.responseEntityExtractor(ResponseHelper.class)
        );
        return response != null ? response.getBody(): null;
    }

    @Override
    public ResponseHelper delete(Integer eId) {
        ResponseEntity<ResponseHelper> response = template.exchange(url + "/" + eId.toString(), HttpMethod.DELETE,null,ResponseHelper.class);
        return response.getBody() != null ? response.getBody() : null;
    }
}