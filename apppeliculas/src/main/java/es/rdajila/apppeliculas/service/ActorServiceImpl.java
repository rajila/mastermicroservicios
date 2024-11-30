package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ActorServiceImpl implements IActorService{
    private final RestTemplate template;

    @Autowired
    public ActorServiceImpl(RestTemplate template) {
        this.template = template;
    }

    String url = "http://localhost:6666/api/actores";
    @Override
    public List<Actor> getAll() {
        Actor[] dataList = template.getForObject(url, Actor[].class);
        return dataList != null ? Arrays.asList(dataList) : List.of();
    }
}
