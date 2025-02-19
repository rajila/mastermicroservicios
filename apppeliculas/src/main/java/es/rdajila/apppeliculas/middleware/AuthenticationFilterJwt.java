package es.rdajila.apppeliculas.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilterJwt extends OncePerRequestFilter {
    @Autowired
    private GeneratorJwt tokenGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(ContextUtil.getCurrentUser() != null && ContextUtil.getCurrentAuthentication() != null) {
            boolean tokenVal = tokenGenerator.validateToken(ContextUtil.getCurrentUser().getToken(), request, response);
        }
        filterChain.doFilter(request, response);
    }
}