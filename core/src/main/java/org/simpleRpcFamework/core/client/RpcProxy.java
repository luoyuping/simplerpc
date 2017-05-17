package org.simpleRpcFamework.core.client;

/**
 * Created by luo on 02/05/2017.
 */
public interface RpcProxy {
    <T> T proxyInterface(Client client, final Class<T> serviceInterface);
}
