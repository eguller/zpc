package com.eguller.zpc.config;

import com.eguller.zpc.Application;
import com.eguller.zpc.currency.OpenExchangeRates;
import com.eguller.zpc.currency.RateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Main application configuration.
 *
 * @author eguller
 */
@Configuration
@PropertySource("classpath:persistence.properties")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackageClasses = Application.class)
class ApplicationConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static RateProvider rateProvider(){
        return new OpenExchangeRates();
    }
}