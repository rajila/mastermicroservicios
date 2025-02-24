package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dto.ActorDtoInput;
import es.rdajila.apipeliculas.model.Actor;

import java.util.List;

public interface IActorService {
    List<Actor> getAll();
    Boolean create(ActorDtoInput eActorInput);
    Boolean update(ActorDtoInput eActorInput);
    Boolean delete(Integer eId);
    Actor getById(Integer eId);
}
