package es.rdajila.apppeliculas.dto;

import es.rdajila.apppeliculas.model.Pelicula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDtoIn extends Pelicula {
    private String idsActorStr;
    private String idsGeneroStr;
    private Integer idPais;
    private Integer idDirector;
    private List<Integer> lActores = new ArrayList<>();
    private List<Integer> lGeneros = new ArrayList<>();

    public void init(){
        this.idDirector = this.getDirectorp().getId();
        this.idPais = this.getCountry().getId();
        this.idsGeneroStr = this.getGeneros() .stream().map(el -> el.getId().toString()).collect(Collectors.joining("|"));
        this.idsActorStr = this.getActores().stream().map(el -> el.getId().toString()).collect(Collectors.joining("|"));
    }

    public void preSave() {
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
