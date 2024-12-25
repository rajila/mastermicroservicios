package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Genero;
import es.rdajila.apipeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByActoresEquals(List<Actor> eActores);
//    @Query("select p from Pelicula p join p.actores a join a.user u where u.nombre like %?1%")
//    List<Pelicula> getByActorNombre(String eNombre);
    @Query("select p from Pelicula p join p.actores a where a.id = ?1")
    List<Pelicula> getByActorId(Integer eId);
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
    @Query("select p from Pelicula p join p.generos g where g.id = ?1")
    List<Pelicula> getByGeneroId(Integer eId);
    @Query("select p from Pelicula p left join p.actores a left join p.generos g where (p.titulo like %?1% OR ?1 = '') AND (a.id = ?3 OR ?3 = 0) AND (g.id = ?2 OR ?2 = 0)")
    List<Pelicula> getByTituloOrGeneroIdOrAutorId(String titulo, Integer generoId, Integer autorId);
}