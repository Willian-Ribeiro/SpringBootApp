package com.industries.lunar.rest.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Profile("!test")
@Configuration
@ConfigurationProperties(prefix = "database")
@Getter
@Setter
public class DatabaseConfig {

    /**
     * Jdbc url for database.
     */
    private String url;

    /**
     * Database driver class name.
     */
    private String driverClassName;

    /**
     * Database user.
     */
    private String username;

    /**
     * Database pas sword.
     */
    private String password;

    /**
     * Database poolName.
     */
    private String poolName;

    /**
     * Database maxPoolSize.
     */
    private Integer maxPoolSize;

    /**
     * Database minPoolSize.
     */
    private Integer minPoolSize;

    /**
     * Database maxLifetime.
     */
    private Integer maxLifetime;

    /**
     * Database validationTimeout,
     */
    private Integer validationTimeout;

    /**
     * Database idleTimeout,
     */
    private Integer idleTimeout;

    /**
     * Database connectionTimeout.
     */
    private Integer connectionTimeout;

    /**
    * Creates a DataSource Bean to connect to the database.
    *
    * @return Connection DataSource.
    */
    @Bean
    public DataSource dataSource() throws IOException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(extractSecretValue(username));
        hikariConfig.setPassword(extractSecretValue(password));
        hikariConfig.setPoolName(poolName);
        hikariConfig.setMinimumIdle(minPoolSize);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setValidationTimeout(validationTimeout);
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setIdleTimeout(idleTimeout);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    /**
    * Tests if the provided secret is an existing file. If it's a file, the contents are read and
    * assumed as the password. Otherwise, we assume the password was passed in plain text.
    * @param secret Path to a file containing the password or the password itself.
    * @return The password to be used for connection.
    *
    */
    private static String extractSecretValue(String secret) throws IOException
    {
        Path secretPath = Path.of(secret);
        if (Files.exists(secretPath) && !Files.isDirectory(secretPath)) {
            return Files.readString(secretPath);
        }
        else {
            return secret;
        }
    }

}
