package es.rdajila.apipeliculas.service;

import es.rdajila.apipeliculas.dto.PeliculaDtoInput;
import es.rdajila.apipeliculas.model.Pelicula;

import java.util.List;

public interface IPeliculaService {
    List<Pelicula> getAll();
    Boolean create(PeliculaDtoInput ePeliculaInput);
    List<Pelicula> getByActorId(Integer eActorId);
    Boolean update(PeliculaDtoInput ePeliculaInput);
    Boolean delete(Integer eId);
    List<Pelicula> getByTitulo(String eTitulo);
    List<Pelicula> getByGeneroId(Integer eGeneroId);
    List<Pelicula> getByTituloOrGeneroIdOrAutorId(String eTitulo, Integer eGeneroId, Integer eAutorId);
    Pelicula getById(Integer eId);
}