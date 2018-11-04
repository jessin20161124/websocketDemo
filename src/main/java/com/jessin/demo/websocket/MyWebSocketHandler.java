package com.jessin.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zexin.guo
 * @create 2018-06-27 下午8:11
 **/
@Service
public class MyWebSocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void pushAllUser(String message) {
        for (Map.Entry<String, WebSocketSession> idSessionEntry : sessionMap.entrySet()) {
            try {
                idSessionEntry.getValue().sendMessage(new TextMessage(message));
                logger.info("向会话：{}推送消息：{}", idSessionEntry.getKey(), message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
        logger.info("会话：{}，建立成功", session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("通过会话：{}，uri：{}，获得消息：{}", session.getId(), session.getUri(), message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sessionMap.remove(session.getId());
        logger.error("会话：{}，传输出错", session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        logger.info("会话：{}，关闭成功", session);
    }
}
