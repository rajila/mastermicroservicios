package es.uah.rdajila.apigateway.repository;

import es.uah.rdajila.apigateway.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoAndEstado(String eEmail, Integer eEstado);
}
