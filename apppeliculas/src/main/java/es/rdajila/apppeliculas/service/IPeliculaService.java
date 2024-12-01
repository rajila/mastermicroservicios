package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.PeliculaFiltroDtoIn;
import es.rdajila.apppeliculas.dto.PeliculaDtoIn;
import es.rdajila.apppeliculas.model.Pelicula;

import java.util.List;

public interface IPeliculaService {
    List<Pelicula> getAll();
    void delete(Integer eId);
    List<Pelicula> getAllByFiltro(PeliculaFiltroDtoIn eFiltro);
    Pelicula getById(Integer eId);
    void save(PeliculaDtoIn ePelicula);
}
