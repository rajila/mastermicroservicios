package es.rdajila.apipeliculas.api.service;

import es.rdajila.apipeliculas.middleware.ContextUtil;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RolApiService implements IRolApiService{
    private final RestTemplate template;
    private final String url = "http://localhost:8090/api/roles";

    @Autowired
    public RolApiService(RestTemplate template) {
        this.template = template;
    }

    @Override
    public ResponseHelper getByCode(String eCode) {
        ResponseEntity<ResponseHelper> response = template.exchange(url + "/codigo/" + eCode, HttpMethod.GET,new HttpEntity<ResponseHelper>(null, ContextUtil.getHeaders()),ResponseHelper.class);
        return response.getBody() != null ? response.getBody() : null;
        //return template.getForObject(url + "/codigo/" + eCode, ResponseHelper.class);
    }
}
