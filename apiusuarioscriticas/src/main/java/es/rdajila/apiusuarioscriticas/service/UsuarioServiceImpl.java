package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dao.IUsuarioDao;
import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import es.rdajila.apiusuarioscriticas.model.Documento;
import es.rdajila.apiusuarioscriticas.model.Rol;
import es.rdajila.apiusuarioscriticas.model.Usuario;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ErrorHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final IUsuarioDao usuarioDao;
    private final IDocumentoService documentoService;
    private final IRolService rolService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(IUsuarioDao usuarioDao,
                              IDocumentoService documentoService,
                              IRolService rolService,
                              PasswordEncoder passwordEncoder) {
        this.usuarioDao = usuarioDao;
        this.documentoService = documentoService;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseHelper create(UsuarioDtoIn eUsuario) {
        ResponseHelper _result = new ResponseHelper();
        eUsuario.setId(null);
        validate(eUsuario, _result, true);
        if (_result.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            save(eUsuario, _result);
        }
        return _result;
    }

    @Override
    public ResponseHelper update(UsuarioDtoIn eUsuario) {
        ResponseHelper _result = new ResponseHelper();
        validate(eUsuario, _result, false);
        if (_result.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            save(eUsuario, _result);
        }
        return _result;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioDao.getAll();
    }

    @Override
    public Usuario getById(int eId) {
        return usuarioDao.getById(eId).orElse(null);
    }

    @Override
    public ResponseHelper delete(int eId) {
        ResponseHelper _result = new ResponseHelper();
        Boolean eliminado = usuarioDao.delete(eId);
        if (!eliminado) {
            _result.setStatus(ConstantsHelper.FAILURE);
            _result.getErrors().add(new ErrorHelper("entity", "Error al eliminar el usuario"));
        }
        return _result;
    }

    private void validate(UsuarioDtoIn eEntDao, ResponseHelper _result, Boolean eIsNew) {
        Usuario dbUsuario = usuarioDao.getById(eEntDao.getId() != null ? eEntDao.getId() : 0).orElse(null);
        // Verifica si el usuario no es nuevo
        if (!eIsNew) {
            if (dbUsuario == null) {
                _result.setIdData(eEntDao.getId());
                _result.setStatus(ConstantsHelper.FAILURE);
                _result.getErrors().add(new ErrorHelper("entity","No existe la entidad de usuario!"));
                return;
            }
        }

        // Validate rol
        Rol dbRol = rolService.getById(eEntDao.getRolId() != null ? eEntDao.getRolId() : 0);
        if (dbRol == null) {
            _result.getErrors().add(new ErrorHelper("rol","Rol no valido!"));
        }

        // validate password
        if(dbUsuario == null && (eEntDao.getPassword() == null || eEntDao.getPassword().isEmpty())) {
            _result.setStatus(ConstantsHelper.FAILURE);
            _result.getErrors().add(new ErrorHelper("password", "El password no puede ser vacio"));
        }else if(dbUsuario != null &&
                (eEntDao.getPassword() == null || eEntDao.getPassword().isEmpty())){
            eEntDao.setPassword(dbUsuario.getPassword());
        }else{
            eEntDao.setPassword(passwordEncoder.encode((eEntDao.getPassword())));
        }

        // Validate existe usuario con email

        _result.setIdData(0);
        if (!_result.getErrors().isEmpty()) _result.setStatus(ConstantsHelper.FAILURE);
    }

    private ResponseHelper getDocumento(UsuarioDtoIn eUsuario) {
        Documento _resultDoc = documentoService.getById(eUsuario.getDocumentoId() != null ? eUsuario.getDocumentoId() : 0);
        if (_resultDoc == null) {
            return documentoService.create(
                    new Documento(eUsuario.getTipoOrigenDocumento(),
                            eUsuario.getTipoDocumento(),
                            eUsuario.getBase64()
                    )
            );
        }else{
            _resultDoc.setBase64(eUsuario.getBase64());
            return documentoService.update(_resultDoc);
        }
    }

    private void mapingUsuario(UsuarioDtoIn eUsuarioSource, Usuario eUsuarioTarget) {
        Rol dbRol = rolService.getById(eUsuarioSource.getRolId());
        Documento dbDoc = documentoService.getById(eUsuarioSource.getDocumentoId());
        eUsuarioTarget.setNombre(eUsuarioSource.getNombre().trim());
        eUsuarioTarget.setApellido(eUsuarioSource.getApellido().trim());
        eUsuarioTarget.setCorreo(eUsuarioSource.getCorreo().trim());
        eUsuarioTarget.setPassword(eUsuarioSource.getPassword().trim());
        eUsuarioTarget.setEstado(eUsuarioSource.getEstado());
        eUsuarioTarget.getRoles().add(dbRol);
        eUsuarioTarget.setDocumento(dbDoc);
    }

    private void save(UsuarioDtoIn eUsuario, ResponseHelper _result) {
        ResponseHelper _resultSaveDoc = getDocumento(eUsuario);
        if(_resultSaveDoc.getStatus().equals(ConstantsHelper.SUCCESS)) {
            Usuario _userOK = usuarioDao.getById(eUsuario.getId() != null ? eUsuario.getId() : 0).orElse(new Usuario());
            eUsuario.setDocumentoId(_resultSaveDoc.getIdData());
            mapingUsuario(eUsuario, _userOK);
            Usuario dbUsuario = usuarioDao.save(_userOK);
            _result.setIdData(dbUsuario.getId());
        } else {
            _result.setStatus(_resultSaveDoc.getStatus());
            _result.getErrors().add(new ErrorHelper("documento","Error al crear el documento!"));
            _result.getErrors().addAll(_resultSaveDoc.getErrors());
        }
    }

    @Override
    public Optional<Usuario> getByCorreoAndEstado(String eCorreo, Integer eEstado) {
        return usuarioDao.getByCorreoAndEstado(eCorreo, eEstado);
    }
}