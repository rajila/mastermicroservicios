package es.rdajila.apiusuarioscriticas.repository;

import es.rdajila.apiusuarioscriticas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}