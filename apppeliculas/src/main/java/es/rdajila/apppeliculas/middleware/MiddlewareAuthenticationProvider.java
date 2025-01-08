package es.rdajila.apppeliculas.middleware;

import es.rdajila.apppeliculas.dto.LoginDtoIn;
import es.rdajila.apppeliculas.dto.LoginDtoOut;
import es.rdajila.apppeliculas.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MiddlewareAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private IAuthService authService;

    public MiddlewareAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginDtoOut usuarioLogueado = authService.login(new LoginDtoIn(authentication.getName(),authentication.getCredentials().toString()));
        if (usuarioLogueado != null) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority(usuarioLogueado.getRol()));
            final UserDetails principal = new UserCurrent(authentication.getName(), authentication.getCredentials().toString(), grantedAuths,usuarioLogueado.getAccessToken(),usuarioLogueado.getNombres(),usuarioLogueado.getRol());
            return new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials().toString(), grantedAuths);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean supports(final Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}