package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dto.PeliculaDtoInput;
import es.rdajila.apipeliculas.model.Pelicula;

import java.util.List;

public interface IPeliculaService {
    List<Pelicula> getAll();
    Boolean create(PeliculaDtoInput ePeliculaInput);
    List<Pelicula> getByActorId(Integer eActorId);
}