package es.rdajila.apppeliculas;

import es.rdajila.apppeliculas.middleware.RestTemplateHeaderModifierInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApppeliculasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApppeliculasApplication.class, args);
	}

	// Para comunicarme con otras APIS REST
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateHeaderModifierInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

}
