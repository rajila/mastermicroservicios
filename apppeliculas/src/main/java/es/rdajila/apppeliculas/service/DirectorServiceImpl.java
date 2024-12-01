package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DirectorServiceImpl implements IDirectorService{
    private final RestTemplate template;

    String url = "http://localhost:8081/api/directores";

    @Autowired
    public DirectorServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Director> getAll() {
        Director[] dataList = template.getForObject(url, Director[].class);
        return dataList != null ? Arrays.asList(dataList) : List.of();
    }
}
