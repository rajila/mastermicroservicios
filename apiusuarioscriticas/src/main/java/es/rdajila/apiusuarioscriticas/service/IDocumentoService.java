package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.model.Documento;

public interface IDocumentoService {
    Documento save(Documento eDocumento);
    Documento getById(int eId);
    Boolean delete(int eId);
}
