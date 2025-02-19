package es.rdajila.apppeliculas.middleware;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class GeneratorJwt {
    SecretKeySpec key = new SecretKeySpec("cmRhamlsYTIwMjQkQA==cmRhamlsYTIwMjQkQA==cmRhamlsYTIwMjQkQA==".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    public boolean validateToken(String token, HttpServletRequest request, HttpServletResponse response) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            Authentication auth = ContextUtil.getCurrentAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            //throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect!!!",ex.fillInStackTrace());
        }
        return false;
    }
}