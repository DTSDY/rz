package com.sy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.VO.MenuVo;
import com.sy.VO.UserVo;
import com.sy.pojo.Role;
import com.sy.pojo.User;
import com.sy.service.MenuService;
import com.sy.service.RoleService;
import com.sy.service.UserRoleService;
import com.sy.service.UserService;
import com.sy.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * ssssyy
 * 2019/10/29 11:07
 */
@Controller
@RequestMapping("/sys")
@CrossOrigin(value = "http://localhost:8081")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/login")
    @ResponseBody
    public R logic(@RequestBody UserVo userVo, HttpSession session) {
        if (!userVo.getCaptcha().equalsIgnoreCase((String) session.getAttribute("captcha"))) {
            return R.error("验证码错误!");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUsername(), userVo.getPassword());
//        if (rememberMe==true){
//            token.setRememberMe(true);
//        }
        SecurityUtils.getSubject().login(token);
        return R.ok();
    }

    @RequestMapping("/user/info")
    @ResponseBody
    public R userInfo() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findUserByUsername(username);
//        System.out.println(user.toString());
        return R.ok().put("user", user);
    }

    @RequestMapping("/menu/user")
    @ResponseBody
    public R menuUser() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<MenuVo> menuVos = menuService.findFatherMenuByUsername(username);
        //一查询到所有包含的信息
        HashSet<String> AllPerm = new HashSet<>();
        List<String> perms = menuService.findPermsByUsername(username);
//        System.out.println(perms);
        for (String perm : perms) {
            if (perm == null || perm.trim().length() == 0) {
                continue;
            } else {
                if (perm.indexOf(",") != -1) {
                    String[] split = perm.split(",");
                    for (String s : split) {
                        AllPerm.add(s);
                    }
                } else {
                    AllPerm.add(perm);
                }
            }
        }
//        System.out.println(AllPerm.toString());
        return R.ok().put("menuList", menuVos).put("permissions", AllPerm);
    }

    @RequestMapping("/user/list")
    @ResponseBody
    public R userList(String order, Integer limit, Integer offset) {
        offset = offset / 3 + 1;
        PageHelper.startPage(offset, limit);
        List<User> allUser = userService.findAllUser(order);
        PageInfo<User> pageInfo = new PageInfo<>(allUser);
        return R.ok().put("total", pageInfo.getTotal()).put("rows", pageInfo.getList());
    }

    @RequestMapping("/roles")
    @ResponseBody
    public R roleList() {
//        offset = offset/3 +1;
//        PageHelper.startPage(offset,limit);
        List<Role> allRole = roleService.findAllRole();
//        PageInfo<Role> pageInfo = new PageInfo<>(allRole);
//        return R.ok().put("total",pageInfo.getTotal()).put("rows",pageInfo.getList());
        return R.ok().put("roles", allRole);
    }

    @RequestMapping("/user/save")
    @ResponseBody
    public R userList(@RequestBody User user, @RequestBody ArrayList<Integer> roles) {
        System.out.println(user.toString());
        System.out.println(roles.toString());
        String salt = UUID.randomUUID().toString();
        String newPass = new Sha256Hash(user.getPassword(), salt, 10000).toBase64();
        user.setPassword(newPass);
        user.setSalt(salt);
        Integer integer = userService.addUser(user);
//        Integer integer1 = userRoleService.addUserRole(user.getUserId(), roles);
        for (Integer role : roles) {
            Integer integer1 = userRoleService.addUserRole(user.getUserId(), role);
            if (integer1 != 1) {
                integer = 0;
            }
        }
//        for (int i = 0; i < Array.getLength(roles); i++) {
//            Integer integer1 = userRoleService.addUserRole(user.getUserId(), );
//            if (integer1 != 1) {
//                integer = 0;
//            }
//        }
        if (integer == 1) {
            return R.ok();
        } else {
            return R.error("发生异常错误！");
        }
    }
}
