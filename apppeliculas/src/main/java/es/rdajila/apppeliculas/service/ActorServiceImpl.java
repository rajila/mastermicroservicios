package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.ActorDtoIn;
import es.rdajila.apppeliculas.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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

    String url = "http://localhost:8090/api/actores";
    @Override
    public List<Actor> getAll() {
        try {
            ResponseEntity<Actor[]> response = template.exchange(url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    Actor[].class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return List.of();
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
        try {
            if (eActor.getId() != null && eActor.getId() > 0) {
                //template.put(url, eActor);
                ResponseEntity<String> response = template.exchange(url,
                        HttpMethod.PUT,
                        new HttpEntity<>(eActor, null),
                        String.class);
            } else {
                eActor.setId(0);
                template.postForObject(url, eActor, String.class);
            }
        } catch(RestClientException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
