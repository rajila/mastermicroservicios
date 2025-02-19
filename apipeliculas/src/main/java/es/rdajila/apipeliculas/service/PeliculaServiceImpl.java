package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.api.service.ICrticaApiService;
import es.rdajila.apipeliculas.dao.*;
import es.rdajila.apipeliculas.dto.PeliculaDtoInput;
import es.rdajila.apipeliculas.model.*;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ErrorHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculaServiceImpl implements IPeliculaService{
    private final IPeliculaDao peliculaDao;
    private final IDirectorDao directorDao;
    private final IGeneroDao generoDao;
    private final IPaisDao paisDao;
    private final IActorDao actorDao;
    private final IActorService actorService;
    private final IDirectorService directorService;
    private final ICrticaApiService crticaApiService;

    @Autowired
    public PeliculaServiceImpl(IPeliculaDao peliculaDao,
                               IDirectorDao directorDao,
                               IGeneroDao generoDao,
                               IPaisDao paisDao,
                               IActorDao actorDao,
                               IActorService actorService,
                               IDirectorService directorService,
                               ICrticaApiService crticaApiService) {
        this.peliculaDao = peliculaDao;
        this.directorDao = directorDao;
        this.generoDao = generoDao;
        this.paisDao = paisDao;
        this.actorDao = actorDao;
        this.actorService = actorService;
        this.directorService = directorService;
        this.crticaApiService = crticaApiService;
    }

    @Override
    public List<Pelicula> getAll() {
        return peliculaDao.getAll().stream().peek(el -> {
            List<Actor> actorList = new ArrayList<>();
            el.getActores().forEach(actor -> {
                Actor _actor = actorService.getById(actor.getId());
                actorList.add(_actor);
            });
            el.getActores().clear();
            el.getActores().addAll(actorList);
            if(el.getDirectorp() != null) {
                Director _director = directorService.getById(el.getDirectorp().getId());
                el.getDirectorp().setNombre(_director.getNombre());
                el.getDirectorp().setApellido(_director.getApellido());
            }
        }).toList();
    }

    @Override
    public Boolean create(PeliculaDtoInput ePeliculaInput) {
        Director directorObj = directorDao.getById(ePeliculaInput.getIdDirector()).orElse(null);
        Pais paisObj = paisDao.getById(ePeliculaInput.getIdPais());

        if (directorObj != null && paisObj != null) {
            Pelicula pelicula = new Pelicula();
            pelicula.setTitulo(ePeliculaInput.getTitulo());
            pelicula.setAnio(ePeliculaInput.getAnio());
            pelicula.setDuracion(ePeliculaInput.getDuracion());
            pelicula.setSinopsis(ePeliculaInput.getSinopsis());
            pelicula.setPortada(ePeliculaInput.getPortada());
            pelicula.setCountry(paisObj);
            pelicula.setDirectorp(directorObj);

            // Agregamos los actores
            ePeliculaInput.getLActores().forEach(id -> {
                actorDao.getById(id).ifPresent(actorObj -> pelicula.getActores().add(actorObj));
            });

            // Agregamos los generos
            ePeliculaInput.getLGeneros().forEach(id -> {
                generoDao.getById(id).ifPresent(generoObj -> pelicula.getGeneros().add(generoObj));
            });

            return peliculaDao.save(pelicula).orElse(null) != null;
        }
        return false;
    }

    @Override
    public List<Pelicula> getByActorId(Integer eActorId) {
        return peliculaDao.getAllByActorId(eActorId);
    }

    @Override
    @Transactional
    public Boolean update(PeliculaDtoInput ePeliculaInput) {
        Director directorObj = directorDao.getById(ePeliculaInput.getIdDirector()).orElse(null);
        Pais paisObj = paisDao.getById(ePeliculaInput.getIdPais());
        Pelicula pelicula = peliculaDao.getById(ePeliculaInput.getId()).orElse(null);

        if (directorObj != null && paisObj != null && pelicula != null) {
            pelicula.setTitulo(ePeliculaInput.getTitulo());
            pelicula.setAnio(ePeliculaInput.getAnio());
            pelicula.setDuracion(ePeliculaInput.getDuracion());
            pelicula.setSinopsis(ePeliculaInput.getSinopsis());
            pelicula.setPortada(ePeliculaInput.getPortada());
            pelicula.setCountry(paisObj);
            pelicula.setDirectorp(directorObj);

            // Eliminamos los actores y/o generos
            pelicula.getActores().clear();
            pelicula.getGeneros().clear();

            // Agregamos los actores
            ePeliculaInput.getLActores().forEach(id -> {
                actorDao.getById(id).ifPresent(actorObj -> pelicula.getActores().add(actorObj));
            });

            // Agregamos los generos
            ePeliculaInput.getLGeneros().forEach(id -> {
                generoDao.getById(id).ifPresent(generoObj -> pelicula.getGeneros().add(generoObj));
            });

            return peliculaDao.save(pelicula).orElse(null) != null;
        }

        return false;
    }

    @Override
    public Boolean delete(Integer eId) {
        ResponseHelper eliminadoCriticas = crticaApiService.deleteByPeliculaId(eId);

        if(eliminadoCriticas.getStatus().compareTo(ConstantsHelper.SUCCESS) == 0) {
            Pelicula peliculaObj = peliculaDao.getById(eId).orElse(null);
            if (peliculaObj != null) {
                return peliculaDao.delete(peliculaObj);
            }
        }
        return false;
    }

    @Override
    public List<Pelicula> getByTitulo(String eTitulo) {
        return peliculaDao.getByTitulo(eTitulo).stream().peek(el -> {
            List<Actor> actorList = new ArrayList<>();
            el.getActores().forEach(actor -> {
                Actor _actor = actorService.getById(actor.getId());
                actorList.add(_actor);
            });
            el.getActores().clear();
            el.getActores().addAll(actorList);
            if(el.getDirectorp() != null) {
                Director _director = directorService.getById(el.getDirectorp().getId());
                el.getDirectorp().setNombre(_director.getNombre());
                el.getDirectorp().setApellido(_director.getApellido());
            }
        }).toList();
    }

    @Override
    public List<Pelicula> getByGeneroId(Integer eGeneroId) {
        return peliculaDao.getByGeneroId(eGeneroId).stream().peek(el -> {
            List<Actor> actorList = new ArrayList<>();
            el.getActores().forEach(actor -> {
                Actor _actor = actorService.getById(actor.getId());
                actorList.add(_actor);
            });
            el.getActores().clear();
            el.getActores().addAll(actorList);
            if(el.getDirectorp() != null) {
                Director _director = directorService.getById(el.getDirectorp().getId());
                el.getDirectorp().setNombre(_director.getNombre());
                el.getDirectorp().setApellido(_director.getApellido());
            }
        }).toList();
    }

    @Override
    public List<Pelicula> getByTituloOrGeneroIdOrAutorId(String eTitulo, Integer eGeneroId, Integer eAutorId) {
        eTitulo = eTitulo == null ? "" : eTitulo;
        eGeneroId = eGeneroId == null ? 0 : eGeneroId;
        eAutorId = eAutorId == null ? 0 : eAutorId;
        return peliculaDao.getByTituloOrGeneroIdOrAutorId(eTitulo, eGeneroId, eAutorId).stream().peek(el -> {
            List<Actor> actorList = new ArrayList<>();
            el.getActores().forEach(actor -> {
                Actor _actor = actorService.getById(actor.getId());
                actorList.add(_actor);
            });
            el.getActores().clear();
            el.getActores().addAll(actorList);
            if(el.getDirectorp() != null) {
                Director _director = directorService.getById(el.getDirectorp().getId());
                el.getDirectorp().setNombre(_director.getNombre());
                el.getDirectorp().setApellido(_director.getApellido());
            }
        }).toList();
    }

    @Override
    public Pelicula getById(Integer eId) {
        Pelicula dataDb = peliculaDao.getById(eId).orElse(null);
        if (dataDb != null) {
            List<Actor> actorList = new ArrayList<>();
            dataDb.getActores().forEach(actor -> {
                Actor _actor = actorService.getById(actor.getId());
                actorList.add(_actor);
            });
            dataDb.getActores().clear();
            dataDb.getActores().addAll(actorList);
            if(dataDb.getDirectorp() != null) {
                Director _director = directorService.getById(dataDb.getDirectorp().getId());
                dataDb.getDirectorp().setNombre(_director.getNombre());
                dataDb.getDirectorp().setApellido(_director.getApellido());
            }
        }
        return dataDb;
    }
}
