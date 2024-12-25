package es.rdajila.apiusuarioscriticas.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.modelmapper.ModelMapper;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    @SuppressWarnings({"removal" })
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/usuarios/**").permitAll()
                        .requestMatchers("/api/roles/**").permitAll()
                        .requestMatchers("/api/documentos/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
