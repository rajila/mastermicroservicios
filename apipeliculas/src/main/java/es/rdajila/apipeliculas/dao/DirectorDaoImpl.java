package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Director;
import es.rdajila.apipeliculas.repository.IDirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DirectorDaoImpl implements IDirectorDao{

    private final IDirectorRepository repository;

    @Autowired
    public DirectorDaoImpl(IDirectorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Director> getById(int eId) {
        return repository.findById(eId);
    }

    @Override
    public List<Director> getAll() {
        return repository.findAll();
    }
}
