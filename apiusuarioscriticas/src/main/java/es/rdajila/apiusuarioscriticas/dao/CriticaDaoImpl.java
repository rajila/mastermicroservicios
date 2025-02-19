package es.rdajila.apiusuarioscriticas.dao;

import es.rdajila.apiusuarioscriticas.model.Critica;
import es.rdajila.apiusuarioscriticas.repository.ICriticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CriticaDaoImpl implements ICriticaDao{
    public final ICriticaRepository repository;

    @Autowired
    public CriticaDaoImpl(ICriticaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Critica save(Critica eCritica) {
        return repository.save(eCritica);
    }

    @Override
    public Optional<Critica> getByPeliculaIdAndUsuarioId(int ePeliculaId, int eUsuarioId) {
        return repository.getByPeliculaIdAndUsuarioId(ePeliculaId, eUsuarioId);
    }

    @Override
    public List<Critica> getAll() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Critica> getByUsuarioId(int eUsuarioId) {
        return repository.getByUsuarioId(eUsuarioId);
    }

    @Override
    public Boolean delete(Integer eId) {
        repository.deleteById(eId);
        return true;
    }

    @Override
    public List<Critica> getByPeliculaId(int ePeliculaId) {
        return repository.findByPeliculaIdOrderByIdDesc(ePeliculaId);
    }

    @Override
    public List<Critica> getAllFilterByNameUserOrPeliculaId(String eNameUser, int ePeliculaId) {
        return repository.getAllFilterByNameUserOrPeliculaId(eNameUser, ePeliculaId);
    }

    @Override
    public List<Critica> getAllFilterByUserLoginAndNameUserOrPeliculaId(String eNameUser, int ePeliculaId, int eUsuarioId) {
        return repository.getAllFilterByUserLoginAndNameUserOrPeliculaId(eNameUser, ePeliculaId, eUsuarioId);
    }

    @Override
    public Boolean deleteByUsuarioId(int eUsuarioId) {
        repository.deleteByUsuarioId(eUsuarioId);
        return true;
    }

    @Override
    public Boolean deleteByPeliculaId(int ePeliculaId) {
        repository.deleteByPeliculaId(ePeliculaId);
        return true;
    }
}
