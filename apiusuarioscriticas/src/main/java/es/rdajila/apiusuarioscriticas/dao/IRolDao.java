package es.rdajila.apiusuarioscriticas.dao;

import es.rdajila.apiusuarioscriticas.model.Rol;

import java.util.Optional;

public interface IRolDao {
    Optional<Rol> getById(int eId);
    Rol getByCode(String eCode);
}
