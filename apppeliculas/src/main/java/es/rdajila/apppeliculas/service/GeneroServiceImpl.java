package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GeneroServiceImpl implements IGeneroService{
    private final RestTemplate template;

    String url = "http://localhost:6666/api/generos";

    @Autowired
    public GeneroServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Genero> getAll() {
        Genero[] generos = template.getForObject(url, Genero[].class);
        return generos != null ? Arrays.asList(generos) : List.of();
    }
}
