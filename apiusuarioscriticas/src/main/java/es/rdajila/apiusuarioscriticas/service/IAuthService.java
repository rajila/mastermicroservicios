package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import es.rdajila.apiusuarioscriticas.model.Usuario;
import lib.rdajila.helper.ResponseHelper;

public interface IAuthService {
    ResponseHelper create(UsuarioDtoIn eUsuario);
    Usuario getUserLogin();
}