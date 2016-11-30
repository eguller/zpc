package com.eguller.zpc.client;

/**
 * (comment)
 *
 * @author eguller
 */
public interface RestClient {
    public <T> T get(String path, Class<T> returnType, String... parameters);
}
