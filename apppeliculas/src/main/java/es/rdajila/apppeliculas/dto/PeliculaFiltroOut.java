package es.rdajila.apppeliculas.dto;

import es.rdajila.apppeliculas.model.Actor;
import es.rdajila.apppeliculas.model.Genero;

import java.util.ArrayList;
import java.util.List;

public class PeliculaFiltroOut {
    private String titulo;
    private List<Actor> actores = new ArrayList<>();
    private List<Genero> generos = new ArrayList<>();

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
