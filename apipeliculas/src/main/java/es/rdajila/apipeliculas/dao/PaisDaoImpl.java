package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Pais;
import es.rdajila.apipeliculas.repository.IPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaisDaoImpl implements IPaisDao {
    private final IPaisRepository repository;

    @Autowired
    public PaisDaoImpl(IPaisRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pais getById(Integer eId) {
        return repository.findById(eId).orElse(null);
    }

    @Override
    public List<Pais> getAll() {
        return repository.findAll();
    }
}
