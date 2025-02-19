package es.rdajila.apppeliculas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Critica {
    private Integer id;
    private String valoracion;
    private Integer nota;
    @JsonFormat(pattern="yyyy-MM-dd", locale = "es-ES", timezone = "Europe/Madrid")
    private Date fecha;
    private Usuario Usuario;
    private Integer peliculaId;
    private String pelicula;
}
