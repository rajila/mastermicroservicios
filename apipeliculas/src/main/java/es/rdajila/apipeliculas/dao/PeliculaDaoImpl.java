package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Pelicula;
import es.rdajila.apipeliculas.repository.IPeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaDaoImpl implements IPeliculaDao{
    private final IPeliculaRepository repository;
    @Autowired
    public PeliculaDaoImpl(IPeliculaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pelicula> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Pelicula> save(Pelicula ePelicula) {
        return Optional.of(repository.save(ePelicula));
    }

    @Override
    public List<Pelicula> getAllByActorList(List<Actor> eActores) {
        return repository.findByActoresEquals(eActores);
    }

    @Override
    public List<Pelicula> getAllByActorId(Integer eActorId) {
        return repository.getByActorId(eActorId);
    }
}