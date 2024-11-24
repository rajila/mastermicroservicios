package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Pelicula;

import java.util.List;
import java.util.Optional;

public interface IPeliculaDao {
    List<Pelicula> getAll();
    Optional<Pelicula> save(Pelicula ePelicula);
    List<Pelicula> getAllByActorList(List<Actor> eActores);
    List<Pelicula> getAllByActorId(Integer eActorId);
}
