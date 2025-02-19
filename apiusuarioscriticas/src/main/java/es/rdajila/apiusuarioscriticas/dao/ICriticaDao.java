package es.rdajila.apiusuarioscriticas.dao;

import es.rdajila.apiusuarioscriticas.model.Critica;

import java.util.List;
import java.util.Optional;

public interface ICriticaDao {
    Critica save(Critica eCritica);
    Optional<Critica> getByPeliculaIdAndUsuarioId(int ePeliculaId, int eUsuarioId);
    List<Critica> getAll();
    List<Critica> getByUsuarioId(int eUsuarioId);
    Boolean delete(Integer eId);
    List<Critica> getByPeliculaId(int ePeliculaId);
    List<Critica> getAllFilterByNameUserOrPeliculaId(String eNameUser, int ePeliculaId);
    List<Critica> getAllFilterByUserLoginAndNameUserOrPeliculaId(String eNameUser, int ePeliculaId, int eUsuarioId);
    Boolean deleteByUsuarioId(int eUsuarioId);
    Boolean deleteByPeliculaId(int ePeliculaId);
}
