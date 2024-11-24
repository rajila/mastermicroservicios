package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGeneroRepository extends JpaRepository<Genero, Integer> {
}