package org.simpleRpcFamework.testdemo.server;


import org.simpleRpcFamework.spring.ServerFactoryBean;
import org.simpleRpcFamework.testdemo.common.HelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by luo on 08/05/2017.
 */
@SpringBootApplication
public class SpringServerConfig {
    @Bean
    public HelloWorld hello() {
        return new HelloWorldImpl();
    }
    @Bean
    public ServerFactoryBean serverFactoryBean() {
        final ServerFactoryBean serverFactoryBean = new ServerFactoryBean();
        serverFactoryBean.setPort(9090);
        serverFactoryBean.setServiceInterface(HelloWorld.class);
        //此处自定义的注册名字就相当于注解了，未来迭代的时候会加入自定义注解方式
        serverFactoryBean.setServiceName("hello");
        serverFactoryBean.setServiceImpl(hello());
        serverFactoryBean.setZkConn("127.0.0.1:2181");
        new Thread(() -> {
            try {
                serverFactoryBean.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "RpcServer").start();
        return serverFactoryBean;
    }
    public static void main(String[] args) {

        SpringApplication.run(SpringServerConfig.class, "--server.port=8082");
    }
}
