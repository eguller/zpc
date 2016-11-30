package com.eguller.zpc.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rest client configuration.
 * Different client implementations can used
 * by changing factory method.
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
