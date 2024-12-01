package es.rdajila.apppeliculas.dto;

import es.rdajila.apppeliculas.model.Pelicula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeliculaIn extends Pelicula {
    private String idsActorStr;
    private String idsGeneroStr;
    private Integer idPais;
    private Integer idDirector;
    private List<Integer> lActores = new ArrayList<>();
    private List<Integer> lGeneros = new ArrayList<>();

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

    public String getIdsActorStr() {
        return idsActorStr;
    }

    public void setIdsActorStr(String idsActorStr) {
        this.idsActorStr = idsActorStr;
    }

    public String getIdsGeneroStr() {
        return idsGeneroStr;
    }

    public void setIdsGeneroStr(String idsGeneroStr) {
        this.idsGeneroStr = idsGeneroStr;
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

    public void init(){
        this.setPortada("/uploads/test.png");
        this.idDirector = this.getDirectorp().getId();
        this.idPais = this.getCountry().getId();
        this.idsGeneroStr = this.getGeneros() .stream().map(el -> el.getId().toString()).collect(Collectors.joining("|"));
        this.idsActorStr = this.getActores().stream().map(el -> el.getId().toString()).collect(Collectors.joining("|"));
    }

    public void preSave() {
        this.setPortada("/uploads/test.png");
        if(!this.idsActorStr.trim().isEmpty()){
            Arrays.stream(this.idsActorStr.split("\\|")).forEach(el -> {
                this.lActores.add(Integer.parseInt(el));
            });
        }

        if(!this.idsGeneroStr.trim().isEmpty()){
            Arrays.stream(this.idsGeneroStr.split("\\|")).forEach(el -> {
                this.lGeneros.add(Integer.parseInt(el));
            });
        }
    }
}
