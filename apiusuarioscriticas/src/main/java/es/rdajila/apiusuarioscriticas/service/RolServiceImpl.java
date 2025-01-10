package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dao.IRolDao;
import es.rdajila.apiusuarioscriticas.model.Rol;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ErrorHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResponseHelper getByCode(String eCode) {
        ResponseHelper _result = new ResponseHelper();
        Rol rol = rolDao.getByCode(eCode);
        if(rol == null) {
            _result.setStatus(ConstantsHelper.FAILURE);
            _result.getErrors().add(new ErrorHelper("entity", "Error al cargar rol"));
        } else _result.setIdData(rol.getId());
        return _result;
    }

    @Override
    public List<Rol> getAll() {
        return rolDao.getAll();
    }
}
