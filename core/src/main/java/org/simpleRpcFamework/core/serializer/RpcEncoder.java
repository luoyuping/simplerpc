package org.simpleRpcFamework.core.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by luo on 02/05/2017.
 */
public class RpcEncoder extends MessageToByteEncoder<Object> {
    private Serializer serializer = new KryoSerializer();
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf out) throws Exception {
        byte[] bytes = serializer.serialize(msg);
        int length = bytes.length;
        out.writeInt(length);
        out.writeBytes(bytes);
    }
}
