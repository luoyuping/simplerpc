package org.simpleRpcFamework.testdemo.client;

import org.simpleRpcFamework.spring.ClientFactoryBean;
import org.simpleRpcFamework.testdemo.common.HelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by luo on 08/05/2017.
 */
@Configuration
@RestController
@SpringBootApplication
@RequestMapping("/test")
public class SpringClientConfig {
    @Bean
    public HelloWorld clientFactoryBean() throws Exception {
        ClientFactoryBean<HelloWorld> clientFactoryBean = new ClientFactoryBean<>();
        clientFactoryBean.setZkConn("127.0.0.1:2181");
        clientFactoryBean.setServiceName("hello");
        clientFactoryBean.setServiceInterface(HelloWorld.class);
        return clientFactoryBean.getObject();
    }
    @Resource
    private HelloWorld helloWorld;
    @RequestMapping("/hello")



    public String hello(String say) {
        return helloWorld.say(say);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringClientConfig.class, "--server.port=8081");
    }
}
