package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.model.Documento;
import lib.rdajila.helper.ResponseHelper;

public interface IDocumentoService {
    ResponseHelper create(Documento eDocumento);
    ResponseHelper update(Documento eDocumento);
    Documento getById(int eId);
    ResponseHelper delete(int eId);
}
