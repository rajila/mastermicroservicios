package es.rdajila.apipeliculas.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDtoInput {
    private Integer id;
    private String titulo;
    private Integer anio;
    private Integer duracion;
    private String sinopsis;
    private String portada;
    private Integer idPais;
    private Integer idDirector;
    private List<Integer> lActores;
    private List<Integer> lGeneros;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public List<Integer> getlActores() {
        return lActores;
    }

    public void setlActores(List<Integer> lActores) {
        this.lActores = lActores;
    }

    public List<Integer> getlGeneros() {
        return lGeneros;
    }

    public void setlGeneros(List<Integer> lGeneros) {
        this.lGeneros = lGeneros;
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
}
