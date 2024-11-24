package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByActoresEquals(Actor actor);
}