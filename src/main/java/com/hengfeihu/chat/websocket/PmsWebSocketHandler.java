package com.hengfeihu.chat.websocket;

import com.alibaba.fastjson.JSONObject;
import com.hengfeihu.chat.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created By Hengfeihu
 *
 * @Date Created in 13:49 2018/8/31
 */
public class PmsWebSocketHandler implements WebSocketHandler {
    private static final String WEBSOCKET_USER = "weboscketUser";
    private static final Logger logger = LoggerFactory.getLogger(PmsWebSocketHandler.class);
    //保存用户链接
    private static ConcurrentHashMap<String, WebSocketSession> users = new ConcurrentHashMap<>();

    //建立连接后处理
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = (User) session.getAttributes().get(WEBSOCKET_USER);
        users.put(session.getId(), session);
        logger.debug(user.getUsername() + "建立连接...");
    }

    //发送消息
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.debug(JSONObject.toJSONString(message));
        User user = (User) session.getAttributes().get(WEBSOCKET_USER);
        for (WebSocketSession webSocketSession : users.values()) {
            JSONObject object = JSONObject.parseObject(message.getPayload().toString());
            TextMessage message1 = new TextMessage(user.getUsername() + ":" + object.getString("msg"));
            webSocketSession.sendMessage(message1);
        }
    }

    //websocket连接异常处理
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        User user = (User) session.getAttributes().get(WEBSOCKET_USER);
        logger.error(user.getUsername() + "连接异常", exception);
        users.remove(session.getId());
    }

    //连接关闭后处理
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        User user = (User) session.getAttributes().get(WEBSOCKET_USER);
        logger.debug(user.getUsername() + "断开连接...");
        users.remove(session.getId());
    }

    //是否支持分包
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
