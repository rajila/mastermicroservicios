package es.rdajila.apipeliculas.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 250)
    private String titulo;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "sinopsis", nullable = false, length = 500)
    private String sinopsis;

    @Column(name = "portada", nullable = false, length = 250)
    private String portada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_director", nullable = false)
    private Director directorp;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_pelicula_actor", joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id"))
    private List<Actor> actores = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_pelicula_genero", joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_genero", referencedColumnName = "id"))
    private List<Genero> generos = new ArrayList<>();

    public Pais getCountry() {
        return country;
    }

    public void setCountry(Pais country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Director getDirectorp() {
        return directorp;
    }

    public void setDirectorp(Director directorp) {
        this.directorp = directorp;
    }

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
}