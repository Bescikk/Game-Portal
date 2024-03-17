package pl.portalgier.gamesinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GamesInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesInfoApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("https://65f6ad01fbe72c5a6852f7de--fanciful-squirrel-ec6e59.netlify.app")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

}
