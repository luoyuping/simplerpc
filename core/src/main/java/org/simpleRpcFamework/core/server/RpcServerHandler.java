package org.simpleRpcFamework.core.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.simpleRpcFamework.core.basevo.Request;
import org.simpleRpcFamework.core.basevo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by luo on 02/05/2017.
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<Request> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServerHandler.class);
    private Object service;
    //此处传入service的实现类对象
    public RpcServerHandler(Object service) {
        this.service = service;
    }
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request msg) throws Exception {
        String methodName = msg.getMethod();
        Object[] params = msg.getParams();
        Class<?>[] parameterTypes = msg.getParameterTypes();
        long requestId = msg.getRequestId();
        //通过反射来获取客户端所要调用的方法并执行
        Method method = service.getClass().getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        Object invoke = method.invoke(service, params);
        Response response = new Response();
        response.setRequestId(requestId);
        response.setResponse(invoke);
        channelHandlerContext.pipeline().writeAndFlush(response);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("Exception caught on {}, ", ctx.channel(), cause);
        ctx.channel().close();
    }
}
