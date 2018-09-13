package com.hengfeihu.chat.controller;

import com.alibaba.fastjson.JSONObject;
import com.hengfeihu.chat.domain.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created By Hengfeihu
 *
 * @Date Created in 14:01 2018/9/10
 */
@Controller
public class Indexcontroller {

    @Value(value = "classpath:user.json")
    private Resource userRes;

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        if (userRes != null) {
            try {
                String userJson = IOUtils.toString(userRes.getInputStream(), Charset.forName("UTF-8"));
                List<User> users = JSONObject.parseArray(userJson, User.class);
                Boolean flag = false;
                if (users != null) {
                    for (User user1 : users) {
                        if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
                            request.getSession().setAttribute("loginUser", user1);
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    return "redirect:/index";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/login";
    }
}
