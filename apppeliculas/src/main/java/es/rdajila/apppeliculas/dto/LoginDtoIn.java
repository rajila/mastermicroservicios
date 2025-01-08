package es.rdajila.apppeliculas.dto;

public class LoginDtoIn {
    public String username;
    public String password;

    public LoginDtoIn() {}

    public LoginDtoIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}