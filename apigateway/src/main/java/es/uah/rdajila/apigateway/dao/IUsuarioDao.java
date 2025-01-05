package es.uah.rdajila.apigateway.dao;

import es.uah.rdajila.apigateway.model.Usuario;

import java.util.Optional;

public interface IUsuarioDao {
    Optional<Usuario> getByCorreoAndEstado(String eCorreo, Integer eEstado);
}
