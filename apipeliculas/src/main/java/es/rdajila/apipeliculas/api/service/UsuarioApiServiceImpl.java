package es.rdajila.apipeliculas.api.service;

import es.rdajila.apipeliculas.api.model.UsuarioApi;
import es.rdajila.apipeliculas.middleware.ContextUtil;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioApiServiceImpl implements IUsuarioApiService {
    private final RestTemplate template;
    private final String url = "http://localhost:8090/api/usuarios";

    @Autowired
    public UsuarioApiServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public UsuarioApi getById(Integer eId) {
        ResponseEntity<UsuarioApi> response = template.exchange(url + "/" + eId,
                HttpMethod.GET,
                new HttpEntity<Integer>(null, ContextUtil.getHeaders()),
                UsuarioApi.class);
        return response.getBody() != null ? response.getBody() : null;
    }

    @Override
    public ResponseHelper create(UsuarioApi usuarioApi) {
        ResponseEntity<ResponseHelper> response = template.exchange(url,
                HttpMethod.POST,
                new HttpEntity<UsuarioApi>(usuarioApi, ContextUtil.getHeaders()),
                ResponseHelper.class);
        return response.getBody() != null ? response.getBody() : null;
        //return template.postForObject(url, usuarioApi, ResponseHelper.class);
    }

    @Override
    public ResponseHelper update(UsuarioApi usuarioApi) {
        ResponseEntity<ResponseHelper> response = template.exchange(url,
                HttpMethod.PUT,
                new HttpEntity<UsuarioApi>(usuarioApi, ContextUtil.getHeaders()),
                ResponseHelper.class);
        return response.getBody() != null ? response.getBody(): null;
    }

    @Override
    public ResponseHelper delete(Integer eId) {
        ResponseEntity<ResponseHelper> response = template.exchange(url + "/" + eId.toString(),
                HttpMethod.DELETE,
                new HttpEntity<Integer>(null, ContextUtil.getHeaders()),
                ResponseHelper.class);
        return response.getBody() != null ? response.getBody() : null;
    }
}