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
public class CriticaApiServiceImpl implements ICrticaApiService {
    private final RestTemplate template;
    private final String url = "http://localhost:8090/api/criticas";

    @Autowired
    public CriticaApiServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public ResponseHelper deleteByPeliculaId(int ePeliculaId) {
        ResponseEntity<ResponseHelper> response = template.exchange(url + "/pelicula/" + ePeliculaId,
                HttpMethod.DELETE,
                new HttpEntity<ResponseHelper>(null, ContextUtil.getHeaders()),ResponseHelper.class);
        return response.getBody() != null ? response.getBody() : null;
    }
}
