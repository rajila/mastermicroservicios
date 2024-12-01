package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.PeliculaFiltroIn;
import es.rdajila.apppeliculas.dto.PeliculaIn;
import es.rdajila.apppeliculas.model.Pelicula;

import java.util.List;

public interface IPeliculaService {
    List<Pelicula> getAll();
    void delete(Integer eId);
    List<Pelicula> getAllByFiltro(PeliculaFiltroIn eFiltro);
    Pelicula getById(Integer eId);
    void save(PeliculaIn ePelicula);
}
