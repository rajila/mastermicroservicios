package es.rdajila.apiusuarioscriticas.repository;

import es.rdajila.apiusuarioscriticas.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
}