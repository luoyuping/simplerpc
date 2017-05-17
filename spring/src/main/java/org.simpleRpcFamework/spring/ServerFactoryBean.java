package org.simpleRpcFamework.spring;
import org.simpleRpcFamework.core.server.Server;
import org.simpleRpcFamework.core.server.ServerBuilder;
import org.simpleRpcFamework.core.server.ServerImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by luo on 08/05/2017.
 */
@lombok.Data
public class ServerFactoryBean implements FactoryBean<Object> {
    private Class<?> serviceInterface;
    private Object serviceImpl;
    private String ip;
    private int port;
    private String serviceName;
    private String zkConn;
    private ServerImpl rpcServer;
    //服务注册并提供
    public void start(){
        Server rpcServer = ServerBuilder
                .builder()
                .serviceImpl(serviceImpl)
                .serviceName(serviceName)
                .zkConn(zkConn)
                .port(port)
                .build();
        rpcServer.start();
    }
    //服务下线
    public void serviceOffline(){
        rpcServer.shutdown();
    }

    @Override
    public Object getObject() throws Exception {
        return this;
    }
    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
}
