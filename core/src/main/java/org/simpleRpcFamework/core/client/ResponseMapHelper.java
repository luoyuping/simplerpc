package org.simpleRpcFamework.core.client;

import org.simpleRpcFamework.core.basevo.Response;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by luo on 02/05/2017.
 */
public class ResponseMapHelper {
    public static ConcurrentMap<Long, BlockingQueue<Response>> responseMap = new ConcurrentHashMap<>();
}
