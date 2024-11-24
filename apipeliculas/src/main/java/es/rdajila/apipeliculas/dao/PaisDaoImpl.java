package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Pais;
import es.rdajila.apipeliculas.repository.IPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
