package org.simpleRpcFamework.core.serializer;

/**
 * Created by luo on 02/05/2017.
 */
public interface Serializer {
    byte[] serialize(Object obj);
    <T> T deserialize(byte[] bytes);
}
