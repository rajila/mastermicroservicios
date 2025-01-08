package es.rdajila.apiusuarioscriticas.repository;

import es.rdajila.apiusuarioscriticas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoAndEstado(String eEmail, Integer eEstado);
    Boolean existsByCorreo(String eCorreo);
}