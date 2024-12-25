package es.rdajila.apipeliculas.api.service;

import es.rdajila.apipeliculas.api.model.UsuarioApi;
import lib.rdajila.helper.ResponseHelper;

public interface IUsuarioApiService {
    UsuarioApi getById(Integer eId);
    ResponseHelper create(UsuarioApi usuarioApi);
    ResponseHelper update(UsuarioApi usuarioApi);
    ResponseHelper delete(Integer eId);
}
