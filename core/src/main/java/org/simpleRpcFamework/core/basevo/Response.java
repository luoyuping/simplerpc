package org.simpleRpcFamework.core.basevo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by luo on 02/05/2017.
 */
@Setter
@Getter
public class Response {
    private long requestId;
    private Object response;
    private Throwable throwable;
}
