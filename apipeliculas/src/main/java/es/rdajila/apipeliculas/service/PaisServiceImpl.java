package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dao.IPaisDao;
import es.rdajila.apipeliculas.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl implements IPaisService {
    private final IPaisDao dao;

    @Autowired
    public PaisServiceImpl(IPaisDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Pais> getAll() {
        return dao.getAll();
    }
}
