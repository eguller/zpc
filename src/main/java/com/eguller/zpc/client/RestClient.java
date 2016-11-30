package com.eguller.zpc.client;

/**
 * All rest clients should implement this interface.
 * By default Spring's RestTemplate is in use.
 *
 * @author eguller
 */
public interface RestClient {
    <T> T get(String path, Class<T> returnType, String... parameters);
}
