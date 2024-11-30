package es.rdajila.apppeliculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApppeliculasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApppeliculasApplication.class, args);
	}

	// Para comunicarme con otras APIS REST
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
