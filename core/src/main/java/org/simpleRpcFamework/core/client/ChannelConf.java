package org.simpleRpcFamework.core.client;
import io.netty.channel.Channel;
import lombok.Data;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
/**
 * Created by luo on 02/05/2017.
 */
@Data
public class ChannelConf {
    private String connStr;
    private String host;
    private int ip;
    private Channel channel;
    private ObjectPool<Channel> channelObjectPool;
    public ChannelConf(String host, int port) {
        this.host = host;
        this.ip = port;
        this.connStr = host + ":" + ip;
        channelObjectPool = new GenericObjectPool<>(new ConnectionObjectFactory(host, port));
    }
    public void close() {
        channelObjectPool.close();
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChannelConf{");
        sb.append("connStr='").append(connStr).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", ip=").append(ip);
        sb.append(", channel=").append(channel);
        sb.append(", channelObjectPool=").append(channelObjectPool);
        sb.append('}');
        return sb.toString();
    }
}
