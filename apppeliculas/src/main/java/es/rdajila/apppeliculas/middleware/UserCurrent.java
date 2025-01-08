package es.rdajila.apppeliculas.middleware;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserCurrent extends User {
    private String token;
    private String nombres;
    private String rol;

    public UserCurrent(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       String token, String nombres, String rol) {
        super(username, password, authorities);
        this.token = token;
        this.nombres = nombres;
        this.rol = rol;
    }

    public UserCurrent(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                       boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                       String token, String nombres, String rol) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.token = token;
        this.nombres = nombres;
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
