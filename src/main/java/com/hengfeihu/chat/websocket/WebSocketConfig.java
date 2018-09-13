package com.hengfeihu.chat.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 自定义websocket配置
 * Created by hengfeihu on 2017/8/4.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public PmsWebSocketHandler pmsWebSocketHandler() {
        return new PmsWebSocketHandler();
    }

    @Bean
    public WebSocketInterceptor pmsWebsocketHandshakeInterceptor() {
        return new WebSocketInterceptor();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //前台 可以使用websocket环境
        registry.addHandler(pmsWebSocketHandler(), "/websocket").addInterceptors(pmsWebsocketHandshakeInterceptor());
        //前台 不可以使用websocket环境，则使用sockjs进行模拟连接
        registry.addHandler(pmsWebSocketHandler(), "/websocket/sockjs").addInterceptors(pmsWebsocketHandshakeInterceptor()).withSockJS();
    }
}
