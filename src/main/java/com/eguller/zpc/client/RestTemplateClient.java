package com.eguller.zpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * (comment)
 *
 * @author eguller
 */
public class RestTemplateClient implements RestClient {
    private static final int CONNECTION_REQUEST_TIMEOUT = 1000;
    private static final int CONNECT_TIMOUT = 1000;
    private static final int READ_TIMEOUT = 5000; //
    private final RestTemplate restTemplate;

    public RestTemplateClient() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
        httpRequestFactory.setConnectTimeout(CONNECT_TIMOUT);
        httpRequestFactory.setReadTimeout(READ_TIMEOUT);
        restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
        ClientHttpRequestFactory requestFactory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        restTemplate.setRequestFactory(requestFactory);
    }

    @Override
    public <T> T get(String path, Class<T> returnType, String... parameters) {
        return restTemplate.getForObject(path, returnType, parameters);
    }
}
