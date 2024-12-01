package es.rdajila.apppeliculas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pelicula {
    private Integer id;
    private String titulo;
    private Integer anio;
    private Integer duracion;
    private String sinopsis;
    private String portada;
    private Director directorp;
    private Pais country;
    private List<Actor> actores = new ArrayList<>();
    private List<Genero> generos = new ArrayList<>();

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Pais getCountry() {
        return country;
    }

    public void setCountry(Pais country) {
        this.country = country;
    }

    public Director getDirectorp() {
        return directorp;
    }

    public void setDirectorp(Director directorp) {
        this.directorp = directorp;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGeneroStr() {
        return this.generos.stream().map(Genero::getNombre).collect(Collectors.joining(" | "));
    }

    public String getAutorStr() {
        return this.actores.stream().map(el -> {
            Usuario _user = el.getUser();
            return _user != null ? _user.getNombre() + " " + _user.getApellido() : "";
        }).collect(Collectors.joining(" | "));
    }
}
