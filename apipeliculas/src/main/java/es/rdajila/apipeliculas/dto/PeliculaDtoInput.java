package es.rdajila.apipeliculas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    private List<Integer> lActores = new ArrayList<>();
    private List<Integer> lGeneros = new ArrayList<>();
}
