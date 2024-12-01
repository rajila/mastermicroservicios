package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImpl implements IPaisService{
    private final RestTemplate template;

    String url = "http://localhost:8081/api/paises";

    @Autowired
    public PaisServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Pais> getAll() {
        Pais[] dataList = template.getForObject(url, Pais[].class);
        return dataList != null ? Arrays.asList(dataList) : List.of();
    }
}
