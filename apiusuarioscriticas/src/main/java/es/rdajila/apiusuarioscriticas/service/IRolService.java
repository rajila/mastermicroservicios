package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.model.Rol;
import lib.rdajila.helper.ResponseHelper;

public interface IRolService {
    Rol getById(int eId);
    ResponseHelper getByCode(String eCode);
}
