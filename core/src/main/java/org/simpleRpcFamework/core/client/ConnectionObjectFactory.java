package org.simpleRpcFamework.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.simpleRpcFamework.core.serializer.RpcDecoder;
import org.simpleRpcFamework.core.serializer.RpcEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by luo on 02/05/2017.
 */
public class ConnectionObjectFactory extends BasePooledObjectFactory<Channel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionObjectFactory.class);
    private String ip;
    private int port;
    public ConnectionObjectFactory(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    private Channel createNewConChannel() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class)
                .group(new NioEventLoopGroup(1))
                .handler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO))
                                .addLast(new RpcDecoder(10 * 1024 * 1024))
                                .addLast(new RpcEncoder())
                                .addLast(new RpcClientHandler())
                        ;
                    }
                });
        try {
            final ChannelFuture f = bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .connect(ip, port).sync();
            f.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    LOGGER.info("Connect success {} ", f);
                }
            });
            final Channel channel = f.channel();
            channel.closeFuture().addListener((ChannelFutureListener) future -> LOGGER.info("Channel Close {} {}", ip, port));
            return channel;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Channel create() throws Exception {
        return createNewConChannel();
    }
    @Override
    public PooledObject<Channel> wrap(Channel obj) {
        //排查出错，之前直接返回个null，未对方法进行重写，导致出错，拿不出对象
        return new DefaultPooledObject<>(obj);
    }
    @Override
    public void destroyObject(PooledObject<Channel> p) throws Exception {
        p.getObject().close().addListener((ChannelFutureListener) future -> LOGGER.info("Close Finish"));
    }
    @Override
    public boolean validateObject(PooledObject<Channel> p) {
        Channel object = p.getObject();
        return object.isActive();
    }
}
