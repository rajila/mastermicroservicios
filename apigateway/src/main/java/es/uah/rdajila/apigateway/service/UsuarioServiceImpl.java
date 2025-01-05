package es.uah.rdajila.apigateway.service;

import es.uah.rdajila.apigateway.dao.IUsuarioDao;
import es.uah.rdajila.apigateway.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    private final IUsuarioDao usuarioDao;

    @Autowired
    public UsuarioServiceImpl(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Optional<Usuario> getByCorreoAndEstado(String eCorreo, Integer eEstado) {
        return usuarioDao.getByCorreoAndEstado(eCorreo, eEstado);
    }
}
