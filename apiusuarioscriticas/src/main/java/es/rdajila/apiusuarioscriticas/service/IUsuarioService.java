package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import es.rdajila.apiusuarioscriticas.model.Usuario;
import lib.rdajila.helper.ResponseHelper;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    ResponseHelper create(UsuarioDtoIn eUsuario);
    ResponseHelper update(UsuarioDtoIn eUsuario);
    List<Usuario> getAll();
    Usuario getById(int eId);
    ResponseHelper delete(int eId);
    Optional<Usuario> getByCorreoAndEstado(String eCorreo, Integer eEstado);
    List<Usuario> getAllFilterRolesAdminOrUser();
    List<Usuario> getByNombresOrCorreoOrRolId(String txt, Integer rolId);
}
