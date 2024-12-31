package es.rdajila.apiusuarioscriticas.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDtoIn {
    private String password;
    private String nombre;
    private String email;
}
