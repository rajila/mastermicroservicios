package es.rdajila.apipeliculas.api.service;

import es.rdajila.apipeliculas.api.model.UsuarioApi;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RolApiService implements IRolApiService{
    private final RestTemplate template;
    private final String url = "http://localhost:8082/api/roles";

    @Autowired
    public RolApiService(RestTemplate template) {
        this.template = template;
    }

    @Override
    public ResponseHelper getByCode(String eCode) {
        return template.getForObject(url + "/codigo/" + eCode, ResponseHelper.class);
    }
}
