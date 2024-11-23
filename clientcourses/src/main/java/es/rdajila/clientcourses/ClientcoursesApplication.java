package es.rdajila.clientcourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientcoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientcoursesApplication.class, args);
	}

	// Para comunicarme con otras APIS REST
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
