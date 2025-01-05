package es.rdajila.apiusuarioscriticas.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDtoIn {
    private String username;
    private String password;
}