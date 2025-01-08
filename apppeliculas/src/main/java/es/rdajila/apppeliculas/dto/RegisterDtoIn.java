package es.rdajila.apppeliculas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDtoIn {
    private String password;
    private String nombre;
    private String email;
}
