package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.api.model.UsuarioApi;
import es.rdajila.apipeliculas.api.service.IRolApiService;
import es.rdajila.apipeliculas.api.service.IUsuarioApiService;
import es.rdajila.apipeliculas.dao.IActorDao;
import es.rdajila.apipeliculas.dao.IPaisDao;
import es.rdajila.apipeliculas.dao.IPeliculaDao;
import es.rdajila.apipeliculas.dto.ActorDtoInput;
import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Pais;
import es.rdajila.apipeliculas.model.Pelicula;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActorServiceImpl implements IActorService{
    private final IActorDao actorDao;
    private final IPaisDao paisDao;
    private final IPeliculaDao peliculaDao;
    private final IUsuarioApiService usuarioApiService;
    private final IRolApiService rolApiService;

    @Autowired
    public ActorServiceImpl(IActorDao actorDao,
                            IPaisDao paisDao,
                            IPeliculaDao peliculaDao,
                            IUsuarioApiService usuarioApiService,
                            IRolApiService rolApiService) {
        this.actorDao = actorDao;
        this.paisDao = paisDao;
        this.peliculaDao = peliculaDao;
        this.usuarioApiService = usuarioApiService;
        this.rolApiService = rolApiService;
    }

    @Override
    public List<Actor> getAll() {
        return actorDao.getAll().stream().peek(el -> {
            UsuarioApi usuarioApi = usuarioApiService.getById(el.getId());
            el.setNombre(usuarioApi.getNombre());
            el.setApellido(usuarioApi.getApellido());
        }).toList();
    }

    @Override
    public Boolean create(ActorDtoInput eActorInput) {
        UsuarioApi _user = new UsuarioApi();
        _user.setId(null);
        _user.setNombre(eActorInput.getNombre());
        _user.setApellido(eActorInput.getApellido());
        _user.setCorreo(eActorInput.getNombre() + "_" + eActorInput.getApellido() + "_" + new Date().getTime() + "@gmail.com");
        _user.setBase64("examplexxx");
        _user.setPassword("examplexxx");
        _user.setEstado(0); // Inactivo
        _user.setDocumentoId(0);
        _user.setTipoDocumento(ConstantsHelper.DOCUMENT_GEN_TYPE);
        _user.setTipoOrigenDocumento(ConstantsHelper.SOURCE_USUARIO_TYPE);

        ResponseHelper responseRolApi = rolApiService.getByCode("ACTOR");
        if (responseRolApi != null && responseRolApi.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            _user.setRolId(responseRolApi.getIdData());
            ResponseHelper responseUsuarioApi = usuarioApiService.create(_user);

            if (responseUsuarioApi != null && responseUsuarioApi.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
                Pais paisObj = paisDao.getById(eActorInput.getIdPais());
                Actor actor = new Actor();
                actor.setId(responseUsuarioApi.getIdData());
                actor.setCountry(paisObj);
                actor.setFechanacimiento(eActorInput.getFechanacimiento());
                return actorDao.save(actor).orElse(null) != null;
            }
        }
        return false;
    }

    @Override
    public Boolean update(ActorDtoInput eActorInput) {
        Actor actorObj = actorDao.getById(eActorInput.getId()).orElse(null);
        UsuarioApi usuarioObj = usuarioApiService.getById(eActorInput.getId());
        Pais paisObj = paisDao.getById(eActorInput.getIdPais());
        ResponseHelper responseRolApi = rolApiService.getByCode("ACTOR");

        if (actorObj != null && usuarioObj != null && paisObj != null &&responseRolApi != null &&
                responseRolApi.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            // Actualizamos la informacion del usuario
            // Pendiente traer informacion de foto perfil de usuario
            usuarioObj.setNombre(eActorInput.getNombre());
            usuarioObj.setApellido(eActorInput.getApellido());
            usuarioObj.setBase64("examplexxx");
            usuarioObj.setTipoDocumento(ConstantsHelper.DOCUMENT_GEN_TYPE);
            usuarioObj.setTipoOrigenDocumento(ConstantsHelper.SOURCE_USUARIO_TYPE);
            usuarioObj.setRolId(responseRolApi.getIdData());
            usuarioObj.setEstado(usuarioObj.getEstado() != null ? usuarioObj.getEstado():0);
            ResponseHelper responseUsuarioApi = usuarioApiService.update(usuarioObj);
            if (responseUsuarioApi != null && responseUsuarioApi.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
                actorObj.setFechanacimiento(eActorInput.getFechanacimiento());
                actorObj.setCountry(paisObj);
                return actorDao.save(actorObj).orElse(null) != null;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Integer eId) {
        Actor actorObj = actorDao.getById(eId).orElse(null);
        if (actorObj != null) {
            List<Pelicula> peliculas = peliculaDao.getAllByActorList(List.of(actorObj));
            for (Pelicula pelicula : peliculas) {
                pelicula.getActores().remove(actorObj);
                peliculaDao.save(pelicula);
            }
            actorDao.delete(actorObj);
            usuarioApiService.delete(actorObj.getId());
            return true;
        }
        return false;
    }

    @Override
    public Actor getById(Integer eId) {
        Actor actor = actorDao.getById(eId).orElse(null);
        if (actor != null) {
            UsuarioApi usuarioApi = usuarioApiService.getById(actor.getId());
            actor.setNombre(usuarioApi.getNombre());
            actor.setApellido(usuarioApi.getApellido());
        }

        return actor;
    }
}
