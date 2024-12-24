package es.rdajila.apiusuarioscriticas.dao;

import es.rdajila.apiusuarioscriticas.model.Documento;

import java.util.Optional;

public interface IDocumentoDao {
    Documento save(Documento eDocumento);
    Optional<Documento> getById(int eId);
    Boolean delete(int eId);
}