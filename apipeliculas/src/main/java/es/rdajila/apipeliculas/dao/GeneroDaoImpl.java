package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Genero;
import es.rdajila.apipeliculas.repository.IGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GeneroDaoImpl implements IGeneroDao {
    private final IGeneroRepository repository;

    @Autowired
    public GeneroDaoImpl(IGeneroRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Genero> getById(int eId) {
        return repository.findById(eId);
    }

    @Override
    public List<Genero> getAll() {
        return repository.findAll();
    }
}
