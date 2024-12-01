package es.rdajila.apppeliculas.seguridad;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeguridadConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
