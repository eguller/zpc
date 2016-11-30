package com.eguller.zpc.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * (comment)
 *
 * @author eguller
 */

@Configuration
public class ClientConfig {
    @Bean
    public RestClient restClient(){
        return new RestTemplateClient();
    }
}
