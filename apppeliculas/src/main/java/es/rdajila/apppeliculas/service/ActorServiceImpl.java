package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.ActorDtoIn;
import es.rdajila.apppeliculas.model.Actor;
import es.rdajila.apppeliculas.model.Pelicula;
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

    @Override
    public void delete(Integer eId) {
        template.delete(url + "/" + eId);
    }

    @Override
    public Actor getById(Integer eId) {
        return template.getForObject(url + "/" + eId, Actor.class);
    }

    @Override
    public void save(ActorDtoIn eActor) {
        //ePelicula.preSave();
        if (eActor.getId() != null && eActor.getId() > 0) {
            template.put(url, eActor);
        } else {
            eActor.setId(0);
            template.postForObject(url, eActor, String.class);
        }
    }
}
