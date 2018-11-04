package com.jessin.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;

/**
 * @author zexin.guo
 * @create 2018-06-27 下午8:03
 **/
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private WebSocketHandler webSocketHandler;

    /**
     * Register {@link WebSocketHandler}s including SockJS fallback options if desired.
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/socketServer")
                .addInterceptors(new MyWebSocketHandlerInterceptor())
                .setAllowedOrigins("*")
                // 使用SockJS，前端也得使用SockJS库。
                .withSockJS()
                // 3s下行发送一次心跳，默认值是25s执行一次
                .setHeartbeatTime(3000)
                .setDisconnectDelay(300);
    }
}
