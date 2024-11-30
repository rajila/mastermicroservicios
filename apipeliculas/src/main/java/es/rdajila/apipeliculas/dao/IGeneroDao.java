package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroDao {
    Optional<Genero> getById(int eId);
    List<Genero> getAll();
}