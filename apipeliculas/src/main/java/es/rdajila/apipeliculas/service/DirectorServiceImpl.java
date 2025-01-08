package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.api.model.UsuarioApi;
import es.rdajila.apipeliculas.api.service.IUsuarioApiService;
import es.rdajila.apipeliculas.dao.IDirectorDao;
import es.rdajila.apipeliculas.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements IDirectorService{

    private final IDirectorDao dao;
    private final IUsuarioApiService usuarioApiService;

    @Autowired
    public DirectorServiceImpl(IDirectorDao dao, IUsuarioApiService usuarioApiService) {
        this.dao = dao;
        this.usuarioApiService = usuarioApiService;
    }

    @Override
    public List<Director> getAll() {
        return dao.getAll().stream().peek(el -> {
            UsuarioApi usuarioApi = usuarioApiService.getById(el.getId());
            el.setNombre(usuarioApi.getNombre());
            el.setApellido(usuarioApi.getApellido());
        }).toList();
    }

    @Override
    public Director getById(Integer eId) {
        Director data = dao.getById(eId).orElse(null);
        if (data != null) {
            UsuarioApi usuarioApi = usuarioApiService.getById(data.getId());
            data.setNombre(usuarioApi.getNombre());
            data.setApellido(usuarioApi.getApellido());
        }

        return data;
    }
}
