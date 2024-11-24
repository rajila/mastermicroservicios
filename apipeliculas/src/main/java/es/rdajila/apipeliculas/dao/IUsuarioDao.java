package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Usuario;

import java.util.Optional;

public interface IUsuarioDao {
    Usuario save(Usuario eUsuario);
    Optional<Usuario> getById(int eId);
}