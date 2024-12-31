package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dto.UsuarioDtoIn;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ErrorHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{
    private final IUsuarioService usuarioService;
    private final IRolService rolService;

    @Autowired
    public AuthServiceImpl(IUsuarioService usuarioService,
                           IRolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @Override
    public ResponseHelper create(UsuarioDtoIn eUsuario) {
        ResponseHelper _result = new ResponseHelper();

        // inicializamos valores
        eUsuario.setEstado(1);
        eUsuario.setDocumentoId(null);
        eUsuario.setTipoOrigenDocumento(ConstantsHelper.SOURCE_USUARIO_TYPE);
        eUsuario.setTipoDocumento(ConstantsHelper.DOCUMENT_GEN_TYPE);
        eUsuario.setBase64("test");

        ResponseHelper rolRH = rolService.getByCode("CLIENTE");
        if (rolRH.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            eUsuario.setRolId(rolRH.getIdData());
            return usuarioService.create(eUsuario);
        } else {
            // Si hay errores al cargar el rol por defecto
            _result.setStatus(rolRH.getStatus());
            _result.getErrors().add(new ErrorHelper("rol", "El rol no existe!!"));
            _result.getErrors().addAll(rolRH.getErrors());
        }
        return _result;
    }
}