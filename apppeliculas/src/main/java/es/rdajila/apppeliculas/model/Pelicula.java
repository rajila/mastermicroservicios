package es.rdajila.apppeliculas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private double nota;
    private int notaEval;

    public String getGeneroStr() {
        return this.generos.stream().map(Genero::getNombre).collect(Collectors.joining(" | "));
    }

    public String getAutorStr() {
        return this.actores.stream().map(el -> {
            return el.getNombre() + " " + el.getApellido();
        }).collect(Collectors.joining(" | "));
    }
}
