package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Director;

import java.util.List;
import java.util.Optional;

public interface IDirectorDao {
    Optional<Director> getById(int eId);
    List<Director> getAll();
}
