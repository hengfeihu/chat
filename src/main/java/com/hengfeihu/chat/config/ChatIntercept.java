package com.hengfeihu.chat.config;

import com.hengfeihu.chat.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By Hengfeihu
 *
 * @Date Created in 16:55 2018/9/12
 */
public class ChatIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith("/static") || request.getRequestURI().equals("/login")) return true;
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
