package es.rdajila.apppeliculas.seguridad;

import es.rdajila.apppeliculas.middleware.MiddlewareAuthenticationProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SeguridadConfig {
    @Autowired
    private MiddlewareAuthenticationProvider authProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    @SuppressWarnings({"removal" })
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        //.defaultSuccessUrl("/actores", true)
                )
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/js/**", "/css/**").permitAll()
                        .requestMatchers( "/login").permitAll()
                        .requestMatchers( "/forbidden").permitAll()
                        .requestMatchers( "/register/**").permitAll()
                        .requestMatchers( "/peliculas/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers( "/actores/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
