package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dao.IDocumentoDao;
import es.rdajila.apiusuarioscriticas.model.Documento;
import lib.rdajila.helper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentoServiceImpl implements IDocumentoService{

    private final IDocumentoDao documentoDao;

    @Autowired
    public DocumentoServiceImpl(IDocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
    }

    @Override
    @Transactional
    public ResponseHelper create(Documento eDocumento) {
        ResponseHelper _result = new ResponseHelper();
        validate(eDocumento, _result, true);
        save(eDocumento, _result);
        return _result;
    }

    @Override
    public ResponseHelper update(Documento eDocumento) {
        ResponseHelper _result = new ResponseHelper();
        validate(eDocumento, _result, false);
        save(eDocumento, _result);
        return _result;
    }

    @Override
    public Documento getById(int eId) {
        return documentoDao.getById(eId).orElse(null);
    }

    @Override
    public ResponseHelper delete(int eId) {
        ResponseHelper _result = new ResponseHelper();
        Boolean eliminado = documentoDao.delete(eId);
        if (!eliminado) {
            _result.setStatus(ConstantsHelper.FAILURE);
            _result.getErrors().add(new ErrorHelper("entity", "Error al eliminar el documento"));
        }
        return _result;
    }

    private void validate (Documento eEnt, ResponseHelper _result, Boolean eIsNew) {
        // Validamos cuando se va a editar un registro
        if (!eIsNew) {
            Documento dbDocumento = documentoDao.getById(eEnt.getId() != null ? eEnt.getId() : 0).orElse(null);
            if (dbDocumento == null) {
                _result.setIdData(eEnt.getId());
                _result.setStatus(ConstantsHelper.FAILURE);
                _result.getErrors().add(new ErrorHelper("entity","No existe la entidad de documento!"));
                return;
            }
        }
        // documento en base64
        if (eEnt.getBase64() == null || eEnt.getBase64().isEmpty()) {
            _result.getErrors().add(new ErrorHelper("base64","Debe ingresar un documento"));
        }
        // tipo valido
        if (!ValidationsHelper.TypeDocumentIsValid(eEnt.getTipo())) {
            _result.getErrors().add(new ErrorHelper("tipo","Tipo no valido!"));
        }
        // tipo source del documento
        if (!ValidationsHelper.TypeSourceIsValid(eEnt.getTipoorigen())) {
            _result.getErrors().add(new ErrorHelper("tipoorigen","Tipo Source no valido!"));
        }

        _result.setIdData(0);
        if (!_result.getErrors().isEmpty()) _result.setStatus(ConstantsHelper.FAILURE);
    }

    private void save(Documento eDocumento, ResponseHelper _result) {
        if (_result.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            Documento documento = documentoDao.save(eDocumento);
            _result.setIdData(documento.getId());
        }
    }
}