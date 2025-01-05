package es.uah.rdajila.apigateway.security;

import es.uah.rdajila.apigateway.jwt.AuthEntryPointJwt;
import es.uah.rdajila.apigateway.jwt.AuthenticationFilterJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SeguridadConfig {
    private final AuthEntryPointJwt authEntryPoint;

    @Autowired
    public SeguridadConfig(AuthEntryPointJwt authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }

    @SuppressWarnings({"removal" })
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/signup/**").permitAll()
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/usuarios/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/roles/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/documentos/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilterJwt jwtAuthenticationFilter() {
        return new AuthenticationFilterJwt();
    }
}
