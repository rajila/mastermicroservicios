package es.rdajila.apipeliculas.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MiddlewareFilterRequest extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Verificar el si existe el TOKEN para pasar a las otras peticionies
        String token = getTokenFromRequest(request);
        String roluser = request.getHeader("roluser");
        if(StringUtils.hasText(token)) {
            // Creamos user custom para guardar el token
            // Servicio no procesa token, solo envia a otro servicio
            UserDetails userDetails = createUserLogin(token, roluser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private UserLogin createUserLogin(String token, String roluser) {
        final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        if (roluser == null || roluser.isEmpty()) {
            return new UserLogin("User", "User", new ArrayList<>(), token);
        }
        grantedAuths.add(new SimpleGrantedAuthority(roluser));
        return new UserLogin("User", "User", grantedAuths, token);
    }
}
