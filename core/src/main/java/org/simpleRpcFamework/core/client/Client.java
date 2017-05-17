package org.simpleRpcFamework.core.client;

import org.simpleRpcFamework.core.basevo.Response;

import java.lang.reflect.Method;

/**
 * Created by luo on 02/05/2017.
 */
public interface Client {
    Response sendMessage(Class<?> clazz, Method method, Object[] args);
    <T> T proxyInterface(Class<T> serviceInterface);
    void close();
}
