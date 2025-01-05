package es.uah.rdajila.apigateway.service;

import es.uah.rdajila.apigateway.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {
    Optional<Usuario> getByCorreoAndEstado(String eCorreo, Integer eEstado);
}
