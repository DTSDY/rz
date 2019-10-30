package com.sy.controller;

import com.sy.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ssssyy
 * 2019/10/29 19:21
 */
@Controller
@CrossOrigin(value = "http://localhost:8081")
public class loginController {
    @RequestMapping("/logout")
    @ResponseBody
    public R logout() {
        SecurityUtils.getSubject().logout();
        return R.ok();
    }

}
