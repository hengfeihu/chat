package com.hengfeihu.chat.websocket;

import com.hengfeihu.chat.domain.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 此类用来获取登录用户信息并交由websocket管理
 * Created By Hengfeihu
 *
 * @Date Created in 13:41 2018/8/31
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        User loginUser = null;
        if (request instanceof ServletServerHttpRequest) {
            if (request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
                request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
            }
            loginUser = (User) ((ServletServerHttpRequest) request).getServletRequest().getSession().getAttribute("loginUser");
            if (loginUser != null) {
                attributes.put("weboscketUser", loginUser);
            }
        }
        return loginUser != null;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
