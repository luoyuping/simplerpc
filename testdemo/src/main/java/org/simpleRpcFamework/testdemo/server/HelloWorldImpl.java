package org.simpleRpcFamework.testdemo.server;

import org.simpleRpcFamework.testdemo.common.HelloWorld;

/**
 * Created by luo on 08/05/2017.
 */
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String say(String hello) {
        return "server: "+hello;
    }
    @Override
    public int sum(int a, int b) {
        return a+b;
    }
    @Override
    public int max(Integer a, Integer b) {
        return a <= b ? b : a;
    }
}
