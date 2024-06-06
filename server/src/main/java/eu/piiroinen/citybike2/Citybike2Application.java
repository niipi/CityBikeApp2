package eu.piiroinen.citybike2;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class Citybike2Application {

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
}
