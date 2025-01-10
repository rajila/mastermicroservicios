package es.rdajila.apppeliculas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String base64;
    private String tipoDocumento;
    private String tipoOrigenDocumento;
    private Integer documentoId;
    private Integer rolId;
    private Integer estado;
    private String rolName;
}