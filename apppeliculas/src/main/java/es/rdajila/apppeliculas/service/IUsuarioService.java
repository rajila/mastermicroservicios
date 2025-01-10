package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Usuario;
import lib.rdajila.helper.ResponseHelper;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> getAll();
    void delete(Integer eId);
    Usuario getById(Integer eId);
    ResponseHelper save(Usuario eUsuario);
    List<Usuario> getAllFilters(String eTxt, Integer eRolId);
}