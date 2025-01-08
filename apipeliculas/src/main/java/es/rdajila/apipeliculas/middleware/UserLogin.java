package es.rdajila.apipeliculas.middleware;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserLogin extends User {
    private String token;

    public UserLogin(String username, String password, Collection<? extends GrantedAuthority> authorities, String token) {
        super(username, password, authorities);
        this.token = token;
    }

    public UserLogin(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String token) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
