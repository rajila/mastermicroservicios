package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Director;

import java.util.Optional;

public interface IDirectorDao {
    Optional<Director> getById(int eId);
}
