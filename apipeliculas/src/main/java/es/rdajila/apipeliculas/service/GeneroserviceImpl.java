package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dao.IGeneroDao;
import es.rdajila.apipeliculas.model.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroserviceImpl implements IGeneroService{
    private final IGeneroDao generoDao;

    @Autowired
    public GeneroserviceImpl(IGeneroDao generoDao) {
        this.generoDao = generoDao;
    }
    @Override
    public List<Genero> getAll() {
        return generoDao.getAll();
    }
}
