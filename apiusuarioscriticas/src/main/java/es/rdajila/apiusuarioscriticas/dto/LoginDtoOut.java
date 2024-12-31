package es.rdajila.apiusuarioscriticas.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDtoOut {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String rol;

    public LoginDtoOut(String accessToken, String rol) {
        this.accessToken = accessToken;
        this.rol = rol;
    }
}
