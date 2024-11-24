package es.rdajila.apipeliculas.dao;

import es.rdajila.apipeliculas.model.Usuario;
import es.rdajila.apipeliculas.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao{
    public final IUsuarioRepository repository;

    @Autowired
    public UsuarioDaoImpl(IUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario save(Usuario eUsuario) {
        return repository.save(eUsuario);
    }
}
