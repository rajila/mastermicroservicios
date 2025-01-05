package es.uah.rdajila.apigateway.dao;

import es.uah.rdajila.apigateway.model.Usuario;
import es.uah.rdajila.apigateway.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao{
    public final IUsuarioRepository repository;

    @Autowired
    public UsuarioDaoImpl(IUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Usuario> getByCorreoAndEstado(String eCorreo, Integer eEstado) {
        return repository.findByCorreoAndEstado(eCorreo, eEstado);
    }
}
