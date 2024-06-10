package eu.piiroinen.citybike2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfiguration {

    @Value("${spring.datasource.username}")
    private String postgresUser;

    @Value("${SPRINGBOOT_PW}")
    private String postgresPassword;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Bean
    public HikariDataSource getDataSource () {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(postgresUser);
        config.setPassword(postgresPassword);
        config.setMaximumPoolSize(4);
        config.setDriverClassName(driver);
        return new HikariDataSource(config);
    }
}
