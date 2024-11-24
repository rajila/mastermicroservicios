package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.repository.IActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDaoImpl implements IActorDao {

    private final IActorRepository repository;

    @Autowired
    public ActorDaoImpl(IActorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Actor> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Actor> save(Actor eActor) {
        return Optional.of(repository.save(eActor));
    }
}
