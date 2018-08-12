package com.jessin.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * @author zexin.guo
 * @create 2018-06-27 下午8:22
 **/
public class MyWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info("{}握手前：{}", request.getRemoteAddress(), request.getURI());
        super.beforeHandshake(request, response, wsHandler, attributes);
        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {
        logger.info("{}握手后：{}", request.getRemoteAddress(), request.getURI());
    }
}
