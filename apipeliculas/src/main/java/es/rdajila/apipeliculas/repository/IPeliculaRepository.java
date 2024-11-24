package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer> {
}