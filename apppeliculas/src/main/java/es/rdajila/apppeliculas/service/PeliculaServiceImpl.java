package es.rdajila.apppeliculas.service;

import es.rdajila.apppeliculas.dto.PeliculaFiltroDtoIn;
import es.rdajila.apppeliculas.dto.PeliculaDtoIn;
import es.rdajila.apppeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PeliculaServiceImpl implements IPeliculaService{
    private final RestTemplate template;

    String url = "http://localhost:8081/api/peliculas";

    @Autowired
    public PeliculaServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Pelicula> getAll() {
        Pelicula[] peliculas = template.getForObject(url, Pelicula[].class);
        return peliculas != null ? Arrays.asList(peliculas) : List.of();
    }

    @Override
    public void delete(Integer eId) {
        template.delete(url + "/" + eId);
    }

    @Override
    public List<Pelicula> getAllByFiltro(PeliculaFiltroDtoIn eFiltro) {
        String _url = url;
        if (eFiltro.getTitulo().compareTo("")==0) {
            _url += "/genero/"+eFiltro.getGeneroId()+"/autor/"+eFiltro.getAutorId();
        } else {
            _url += "/titulo/"+ eFiltro.getTitulo()+"/genero/"+eFiltro.getGeneroId()+"/autor/"+eFiltro.getAutorId();
        }

        Pelicula[] peliculas = template.getForObject(_url, Pelicula[].class);
        return peliculas != null ? Arrays.asList(peliculas) : List.of();
    }

    @Override
    public Pelicula getById(Integer eId) {
        return template.getForObject(url + "/" + eId, Pelicula.class);
    }

    @Override
    public void save(PeliculaDtoIn ePelicula) {
        ePelicula.preSave();
        if (ePelicula.getId() != null && ePelicula.getId() > 0) {
            template.put(url, ePelicula);
        } else {
            ePelicula.setId(0);
            template.postForObject(url, ePelicula, String.class);
        }
    }
}
