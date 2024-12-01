package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.ActorDtoIn;
import es.rdajila.apppeliculas.model.Actor;

import java.util.List;

public interface IActorService {
    List<Actor> getAll();
    void delete(Integer eId);
    Actor getById(Integer eId);
    void save(ActorDtoIn eActor);
}
