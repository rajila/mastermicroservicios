package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByActoresEquals(List<Actor> eActores);

    @Query("select p from Pelicula p join p.actores a join a.user u where u.nombre like %?1%")
    List<Pelicula> getByActorNombre(String eNombre);

    @Query("select p from Pelicula p join p.actores a where a.id = ?1")
    List<Pelicula> getByActorId(Integer eId);
}