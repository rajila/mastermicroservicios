package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.LoginDtoIn;
import es.rdajila.apppeliculas.dto.LoginDtoOut;

public interface IAuthService {
    LoginDtoOut login(LoginDtoIn eData);
}