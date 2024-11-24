package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dao.IActorDao;
import es.rdajila.apipeliculas.dao.IPaisDao;
import es.rdajila.apipeliculas.dao.IPeliculaDao;
import es.rdajila.apipeliculas.dao.IUsuarioDao;
import es.rdajila.apipeliculas.dto.ActorDtoInput;
import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Pais;
import es.rdajila.apipeliculas.model.Pelicula;
import es.rdajila.apipeliculas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActorServiceImpl implements IActorService{

    private final IUsuarioDao usuarioDao;
    private final IActorDao actorDao;
    private final IPaisDao paisDao;
    private final IPeliculaDao peliculaDao;

    @Autowired
    public ActorServiceImpl(IActorDao actorDao, IPaisDao paisDao,IUsuarioDao usuarioDao, IPeliculaDao peliculaDao) {
        this.actorDao = actorDao;
        this.paisDao = paisDao;
        this.usuarioDao = usuarioDao;
        this.peliculaDao = peliculaDao;
    }

    @Override
    public List<Actor> getAll() {
        return actorDao.getAll();
    }

    @Override
    public Boolean create(ActorDtoInput eActorInput) {
        Usuario result = usuarioDao.save(new Usuario(null, eActorInput.getNombre(), eActorInput.getApellido()));
        if (result != null) {
            Pais paisObj = paisDao.getById(eActorInput.getIdPais());
            Actor actor = new Actor();
            actor.setCountry(paisObj);
            actor.setFechanacimiento(eActorInput.getFechanacimiento());
            actor.setUser(result);
            return actorDao.save(actor).orElse(null) != null;
        }
        return false;
    }

    @Override
    public Boolean update(ActorDtoInput eActorInput) {
        Actor actorObj = actorDao.getById(eActorInput.getId()).orElse(null);
        Usuario usuarioObj = usuarioDao.getById(eActorInput.getId()).orElse(null);
        Pais paisObj = paisDao.getById(eActorInput.getIdPais());

        if (actorObj != null && usuarioObj != null && paisObj != null) {
            // Actualizamos la informacion del usuario
            usuarioObj.setNombre(eActorInput.getNombre());
            usuarioObj.setApellido(eActorInput.getApellido());
            usuarioDao.save(usuarioObj);

            actorObj.setFechanacimiento(eActorInput.getFechanacimiento());
            actorObj.setCountry(paisObj);

            return actorDao.save(actorObj).orElse(null) != null;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean delete(Integer eId) {
        Actor actorObj = actorDao.getById(eId).orElse(null);
        if (actorObj != null) {
            List<Pelicula> peliculas = peliculaDao.getAllByActorList(List.of(actorObj));
            for (Pelicula pelicula : peliculas) {
                pelicula.getActores().remove(actorObj);
                peliculaDao.save(pelicula);
            }
            return actorDao.delete(actorObj);
        }
        return false;
    }
}
