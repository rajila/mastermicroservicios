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
    Optional<Pelicula> getById(Integer ePeliculaId);
    Boolean delete(Pelicula ePelicula);
    List<Pelicula> getByTitulo(String eTitulo);
    List<Pelicula> getByGeneroId(Integer eGeneroId);
}
