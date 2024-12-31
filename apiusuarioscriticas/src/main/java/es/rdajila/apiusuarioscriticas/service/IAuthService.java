package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import lib.rdajila.helper.ResponseHelper;

public interface IAuthService {
    ResponseHelper create(UsuarioDtoIn eUsuario);
}
