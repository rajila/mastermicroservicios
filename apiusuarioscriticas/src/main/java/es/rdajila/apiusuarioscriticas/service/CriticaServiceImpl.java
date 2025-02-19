package es.rdajila.apiusuarioscriticas.service;

import es.rdajila.apiusuarioscriticas.dao.ICriticaDao;
import es.rdajila.apiusuarioscriticas.model.Critica;
import es.rdajila.apiusuarioscriticas.model.Usuario;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ErrorHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CriticaServiceImpl implements ICriticaService{
    private final ICriticaDao criticaDao;
    private final IAuthService authService;

    @Autowired
    public CriticaServiceImpl(ICriticaDao criticaDao, IAuthService authService) {
        this.criticaDao = criticaDao;
        this.authService = authService;
    }

    @Override
    public ResponseHelper create(Critica eCritica) {
        ResponseHelper _result = new ResponseHelper();
        eCritica.setId(null);
        validate(eCritica, _result);
        if (_result.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            save(eCritica, _result);
        }
        return _result;
    }

    @Override
    public List<Critica> getAll() {
        Usuario user = getUserCurrent();
        if (user != null && user.getId() > 0 && user.getRoles() != null && !user.getRoles().isEmpty()) {
            if (user.getRolName() != null && user.getRolName().compareToIgnoreCase("user") == 0) {
                return criticaDao.getByUsuarioId(user.getId()); // Usuario que NO es admin
            }
        }
        return criticaDao.getAll(); // Usuario ADMIN
    }

    @Override
    public Critica getByPeliculaIdAndUsuarioLoginId(int ePeliculaId) {
        Usuario user = getUserCurrent();
        int userId = user.getId() != null ? user.getId() : 0;
        return criticaDao.getByPeliculaIdAndUsuarioId(ePeliculaId,userId).orElse(null);
    }

    @Override
    public ResponseHelper delete(int eId) {
        ResponseHelper _result = new ResponseHelper();
        Boolean eliminado = criticaDao.delete(eId);
        if (!eliminado) {
            _result.setStatus(ConstantsHelper.FAILURE);
            _result.getErrors().add(new ErrorHelper("entity", "Error al eliminar la critica"));
        }
        return _result;
    }

    @Override
    public List<Critica> getByPeliculaId(int ePeliculaId) {
        return criticaDao.getByPeliculaId(ePeliculaId);
    }

    @Override
    public List<Critica> getAllFilter(String eNameUser, int ePeliculaId) {
        Usuario user = getUserCurrent();
        if (user != null && user.getId() > 0 && user.getRoles() != null && !user.getRoles().isEmpty()) {
            if (user.getRolName() != null && user.getRolName().compareToIgnoreCase("user") == 0) {
                return criticaDao.getAllFilterByUserLoginAndNameUserOrPeliculaId(eNameUser,ePeliculaId,user.getId()); // Usuario que NO es admin
            }
        }
        return criticaDao.getAllFilterByNameUserOrPeliculaId(eNameUser,ePeliculaId); // Usuario ADMIN
    }

    @Override
    public ResponseHelper deleteByUsuarioId(int eUsuarioId) {
        ResponseHelper _result = new ResponseHelper();
        Boolean eliminado = criticaDao.deleteByUsuarioId(eUsuarioId);
        if (!eliminado) {
            _result.setStatus(ConstantsHelper.FAILURE);
            _result.getErrors().add(new ErrorHelper("entity", "Error al eliminar las criticas"));
        }
        return _result;
    }

    @Override
    @Transactional
    public ResponseHelper deleteByPeliculaId(int ePeliculaId) {
        ResponseHelper _result = new ResponseHelper();
        Boolean eliminado = criticaDao.deleteByPeliculaId(ePeliculaId);
        if (!eliminado) {
            _result.setStatus(ConstantsHelper.FAILURE);
            _result.getErrors().add(new ErrorHelper("entity", "Error al eliminar las criticas"));
        }
        return _result;
    }

    private Usuario getUserCurrent(){
        return authService.getUserLogin();
    }

    private void validate(Critica eEntDao, ResponseHelper _result) {
        // Siempre son NUEVAS criticas
        Usuario user = getUserCurrent();
        if (user == null) {
            _result.getErrors().add(new ErrorHelper("object","Usuario no valido!"));
        }else eEntDao.setUsuario(user);

        if (eEntDao.getValoracion() == null || eEntDao.getValoracion().isEmpty()) {
            _result.getErrors().add(new ErrorHelper("comentario","Debe ingresar un comentario"));
        }

        if (eEntDao.getNota() == null || eEntDao.getNota() <= 0) {
            _result.getErrors().add(new ErrorHelper("nota","Debe dar una nota como minimo 1 estrella"));
        }

        if (eEntDao.getPeliculaId() == null || eEntDao.getPeliculaId() <= 0) {
            _result.getErrors().add(new ErrorHelper("pelicula","Pelicula no valida!"));
        } else if (user != null) {
            Critica _critica = getByPeliculaIdAndUsuarioLoginId(eEntDao.getPeliculaId());
            if (_critica != null) {
                _result.getErrors().add(new ErrorHelper("critica","Ya existe un critica con ese usuario"));
            }
        }

        _result.setIdData(0);
        if (!_result.getErrors().isEmpty()) _result.setStatus(ConstantsHelper.FAILURE);
    }

    private void save(Critica eCritica, ResponseHelper _result) {
        eCritica.setFecha(new Date());
        Critica dbData = criticaDao.save(eCritica);
        _result.setIdData(dbData.getId());
    }
}
