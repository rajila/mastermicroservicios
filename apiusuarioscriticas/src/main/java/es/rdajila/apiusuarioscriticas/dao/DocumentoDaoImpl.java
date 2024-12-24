package es.rdajila.apiusuarioscriticas.dao;

import es.rdajila.apiusuarioscriticas.model.Documento;
import es.rdajila.apiusuarioscriticas.repository.IDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DocumentoDaoImpl implements IDocumentoDao {
    private final IDocumentoRepository repository;

    @Autowired
    public DocumentoDaoImpl(IDocumentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Documento save(Documento eDocumento) {
        return repository.save(eDocumento);
    }

    @Override
    public Optional<Documento> getById(int eId) {
        return repository.findById(eId);
    }

    @Override
    public Boolean delete(int eId) {
        repository.deleteById(eId);
        return true;
    }
}
