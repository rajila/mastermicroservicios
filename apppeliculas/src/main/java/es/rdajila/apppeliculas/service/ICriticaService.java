package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.model.Critica;
import lib.rdajila.helper.ResponseHelper;

import java.util.List;

public interface ICriticaService {
    List<Critica> getAllByPeliculaId(Integer ePeliculaId);
    ResponseHelper getMyCriticaByPeliculaId(Integer ePeliculaId);
    ResponseHelper save(Critica eCritica);
    void delete(Integer eId);
    List<Critica> getAll();
    List<Critica> getAllFilters(String eTxt, Integer ePeliculaId);
}
