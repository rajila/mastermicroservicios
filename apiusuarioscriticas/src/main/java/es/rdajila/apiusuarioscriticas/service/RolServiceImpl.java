package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dao.IRolDao;
import es.rdajila.apiusuarioscriticas.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements IRolService{
    private final IRolDao rolDao;

    @Autowired
    public RolServiceImpl(IRolDao rolDao) {
        this.rolDao = rolDao;
    }

    @Override
    public Rol getById(int eId) {
        return rolDao.getById(eId).orElse(null);
    }
}
