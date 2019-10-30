package com.sy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.VO.MenuVo;
import com.sy.VO.UserVo;
import com.sy.VO.UserVo2;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    public R userList( @RequestBody UserVo2 user) {
//        System.out.println(roles.toString());
//        System.out.println(user.toString());
        String addUsername = (String) SecurityUtils.getSubject().getPrincipal();
        User adduser = userService.findUserByUsername(addUsername);
        String salt = UUID.randomUUID().toString();
        String newPass = new Sha256Hash(user.getPassword(), salt, 10000).toBase64();
        user.setPassword(newPass);
        Integer user_num = userService.addUser(new User(null, user.getUsername(), user.getPassword(), user.getEmail(), user.getMobile(), user.getStatus(), adduser.getUserId(), new Date(), null, user.getSex(), null, salt));
        User nowUser = userService.findUserByUsername(user.getUsername());
        for (Integer role : user.getRoles()) {
            Integer role_num = userRoleService.addUserRole(nowUser.getUserId(), role);
            if (role_num==0){
                user_num=0;
            }
        }
        if (user_num==0){
            return R.error("发生异常错误！");
        }
        return R.ok();
    }

    @RequestMapping("/user/info/{userId}")
    @ResponseBody
    public R queryByUserId(@PathVariable Integer userId){
        UserVo2 user = userService.findUserByUserId(userId);
        return R.ok().put("user", user);
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public R updateUser(@RequestBody UserVo2 user){
//        UserVo2 user = userService.findUserByUserId(userId);
        System.out.println(user.toString());
        return R.ok();
    }

}
