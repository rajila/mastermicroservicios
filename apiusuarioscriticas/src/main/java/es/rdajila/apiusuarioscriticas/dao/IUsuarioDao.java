package es.rdajila.apiusuarioscriticas.dao;

import es.rdajila.apiusuarioscriticas.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioDao {
    Usuario save(Usuario eUsuario);
    Optional<Usuario> getById(int eId);
    List<Usuario> getAll();
    Boolean delete(Integer eId);
}