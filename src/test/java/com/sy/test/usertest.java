package com.sy.test;

import com.sy.pojo.User;
import com.sy.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

/**
 * ssssyy
 * 2019/10/29 11:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class usertest {

    @Autowired
    private UserService userService;

    @Test
    public void testadd(){
        User user = new User(null,"ssssyy","123456","1969696415@qq.com","18637891729",1,1,new Date(),1,"男",null,null);
        String salt = UUID.randomUUID().toString();
        String newPass = new Sha256Hash(user.getPassword(), salt, 10000).toBase64();
        user.setPassword(newPass);
        user.setSalt(salt);
        Integer integer = userService.addUser(user);
        System.out.println(integer);
    }

}
