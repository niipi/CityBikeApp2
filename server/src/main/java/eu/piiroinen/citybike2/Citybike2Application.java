package eu.piiroinen.citybike2;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Citybike2Application {

	private final String LOCALHOST = "http://localhost:3000";

	@Value("${POSTGRES_USER}")
	private String postgresUser;

	@Value("${POSTGRES_PASSWORD}")
	private String postgresPassword;

	public static void main(String[] args) {
		SpringApplication.run(Citybike2Application.class, args);
	}

	@Bean
	public Flyway flyway() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/citybikedata");
		dataSource.setUsername(postgresUser);
		dataSource.setPassword(postgresPassword);

		Flyway flyway = Flyway.configure()
				.dataSource(dataSource)
				.load();
		flyway.migrate();

		return flyway;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/bikestation/**").allowedOrigins(LOCALHOST);
			}
		};
	}
}
