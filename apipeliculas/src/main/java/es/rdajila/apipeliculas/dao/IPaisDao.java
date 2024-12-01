package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Pais;

import java.util.List;

public interface IPaisDao {
    Pais getById(Integer eId);
    List<Pais> getAll();
}