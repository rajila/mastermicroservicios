package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dao.IDocumentoDao;
import es.rdajila.apiusuarioscriticas.model.Documento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoServiceImpl implements IDocumentoService{

    private final IDocumentoDao documentoDao;

    @Autowired
    public DocumentoServiceImpl(IDocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
    }

    @Override
    public Documento save(Documento eDocumento) {
        return documentoDao.save(eDocumento);
    }

    @Override
    public Documento getById(int eId) {
        return documentoDao.getById(eId).orElse(null);
    }

    @Override
    public Boolean delete(int eId) {
        return documentoDao.delete(eId);
    }
}
