package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.LoginDtoIn;
import es.rdajila.apppeliculas.dto.LoginDtoOut;
import es.rdajila.apppeliculas.model.Usuario;
import lib.rdajila.helper.ResponseHelper;

public interface IAuthService {
    LoginDtoOut login(LoginDtoIn eData);
    ResponseHelper register(Usuario eData);
}