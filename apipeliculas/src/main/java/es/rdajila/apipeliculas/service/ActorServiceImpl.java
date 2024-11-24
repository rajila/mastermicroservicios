package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dao.IActorDao;
import es.rdajila.apipeliculas.dao.IPaisDao;
import es.rdajila.apipeliculas.dao.IUsuarioDao;
import es.rdajila.apipeliculas.dto.ActorDtoInput;
import es.rdajila.apipeliculas.model.Actor;
import es.rdajila.apipeliculas.model.Pais;
import es.rdajila.apipeliculas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements IActorService{

    private final IUsuarioDao usuarioDao;
    private final IActorDao actorDao;
    private final IPaisDao paisDao;

    @Autowired
    public ActorServiceImpl(IActorDao actorDao, IPaisDao paisDao,IUsuarioDao usuarioDao) {
        this.actorDao = actorDao;
        this.paisDao = paisDao;
        this.usuarioDao = usuarioDao;
    }

    @Override
    public List<Actor> getAll() {
        return actorDao.getAll();
    }

    @Override
    public Boolean create(ActorDtoInput eActorInput) {
        Usuario result = usuarioDao.save(new Usuario(null, eActorInput.getName(), eActorInput.getSurname()));
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
        return null;
    }
}
