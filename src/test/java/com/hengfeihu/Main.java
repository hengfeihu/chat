package com.hengfeihu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.hengfeihu.chat.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Hengfeihu
 *
 * @Date Created in 17:10 2018/9/12
 */
public class Main {
    public static void main(String[] args) {
        User user1 = new User("衡飞虎", "111");
        User user2 = new User("石方超", "111");
        User user3 = new User("方强", "111");
        User user4 = new User("顾小刚", "111");
        List<User> list=new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        System.out.println(JSON.toJSONString(list));
        String areaData = IOUtils.getStringProperty("user.json");
        System.out.println(areaData);
    }
}
