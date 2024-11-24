package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaisRepository extends JpaRepository<Pais, Integer> {
}