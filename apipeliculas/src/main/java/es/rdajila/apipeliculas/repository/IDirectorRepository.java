package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDirectorRepository extends JpaRepository<Director, Integer> {
}