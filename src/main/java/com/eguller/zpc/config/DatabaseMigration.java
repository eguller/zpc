package com.eguller.zpc.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * (comment)
 *
 * @author eguller
 */
@Configuration
public class DatabaseMigration {
    @Value("${spring.profiles.active}")
    String activeProfile;
    @Autowired
    DataSource dataSource;
    private String driver;

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        if (Profile.DEVELOPMENT.name().equals(activeProfile)) {
            flyway.setLocations("classpath:sql/common", "classpath:sql/dev");
        } else {
            flyway.setLocations("classpath:sql/common");
        }
        flyway.setDataSource(dataSource);
        return flyway;
    }


}
