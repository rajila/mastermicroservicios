package es.rdajila.apppeliculas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDtoIn {
    private Integer id;
    private String valoracion;
    private Integer nota;
    private Integer peliculaId;
}
