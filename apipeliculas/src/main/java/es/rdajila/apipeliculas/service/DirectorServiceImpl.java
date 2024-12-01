package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dao.IDirectorDao;
import es.rdajila.apipeliculas.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements IDirectorService{

    private final IDirectorDao dao;
    @Autowired
    public DirectorServiceImpl(IDirectorDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Director> getAll() {
        return dao.getAll();
    }
}
