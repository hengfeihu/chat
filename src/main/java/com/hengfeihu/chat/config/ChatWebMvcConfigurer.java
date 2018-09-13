package com.hengfeihu.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created By Hengfeihu
 *
 * @Date Created in 17:01 2018/9/12
 */
@Configuration
public class ChatWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ChatIntercept()).addPathPatterns("/**");
    }
}
