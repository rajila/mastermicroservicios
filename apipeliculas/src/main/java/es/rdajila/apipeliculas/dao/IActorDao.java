package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Actor;

import java.util.List;
import java.util.Optional;

public interface IActorDao {
    List<Actor> getAll();
    Optional<Actor> getById(int eId);
    Optional<Actor> save(Actor eActor);
    Boolean delete(Actor eActor);
}