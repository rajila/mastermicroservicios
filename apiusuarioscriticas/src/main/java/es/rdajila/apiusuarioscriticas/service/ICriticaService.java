package es.rdajila.apiusuarioscriticas.service;


import es.rdajila.apiusuarioscriticas.model.Critica;
import lib.rdajila.helper.ResponseHelper;

import java.util.List;

public interface ICriticaService {
    ResponseHelper create(Critica eCritica);
    List<Critica> getAll();
    Critica getByPeliculaIdAndUsuarioLoginId(int ePeliculaId);
    ResponseHelper delete(int eId);
    List<Critica> getByPeliculaId(int ePeliculaId);
    List<Critica> getAllFilter(String eNameUser, int ePeliculaId);
    ResponseHelper deleteByUsuarioId(int eUsuarioId);
    ResponseHelper deleteByPeliculaId(int ePeliculaId);
}
