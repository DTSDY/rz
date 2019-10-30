package com.sy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.VO.MenuVo;
import com.sy.VO.RoleVo;
import com.sy.pojo.Role;
import com.sy.pojo.User;
import com.sy.service.MenuService;
import com.sy.service.RoleMenuService;
import com.sy.service.RoleService;
import com.sy.service.UserService;
import com.sy.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * ssssyy
 * 2019/10/30 11:05
 */
@Controller
@RequestMapping("/sys")
@CrossOrigin(value = "http://localhost:8081")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping("/role/list")
    @ResponseBody
    public R roleList(String order, Integer limit, Integer offset) {
        offset = offset / 3 + 1;
        PageHelper.startPage(offset, limit);
        List<Role> allRoles = roleService.findAllRoles(order);
        PageInfo<Role> pageInfo = new PageInfo<>(allRoles);
        return R.ok().put("total", pageInfo.getTotal()).put("rows", pageInfo.getList());
    }

    @RequestMapping("/menu/select")
    @ResponseBody
    public R menuSelect(){
        List<MenuVo> firstMenu = menuService.findFirstMenu();
        return R.ok().put("menuList", firstMenu);
    }

    @RequestMapping("/role/save")
    @ResponseBody
    public R addRole(@RequestBody RoleVo roleVo){
//        System.out.println(roleVo.toString());
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findUserByUsername(username);
        Role role = new Role(null,roleVo.getRoleName(),roleVo.getRemark(),user.getUserId(),new Date());
        roleService.addRole(role);
        Role nowRole = roleService.findRoleByRoleName(roleVo.getRoleName());
        for (Integer menu : roleVo.getMenus()) {
            roleMenuService.addRoleMenu(nowRole.getRoleId(), menu);
        }
        return R.ok();
    }

    @RequestMapping("/role/info/{roleId}")
    @ResponseBody
    public R findRoleByRoleId(@PathVariable Integer roleId){
        RoleVo role = roleService.findRoleByRoleId(roleId);
//        System.out.println(role);
        return R.ok().put("role",role);
    }

    @RequestMapping("/role/update")
    @ResponseBody
    public R updateRoleMenu(@RequestBody RoleVo roleVo){
//        System.out.println(roleVo);
        roleService.updateRole(new Role(roleVo.getRoleId(),roleVo.getRoleName(),roleVo.getRemark(),null,null));
        roleMenuService.deleteRoleMenu(roleVo.getRoleId());
        for (Integer menu : roleVo.getMenus()) {
            roleMenuService.addRoleMenu(roleVo.getRoleId(), menu);
        }
        return R.ok();
    }

    @DeleteMapping("/roles/{roleId}")
    @ResponseBody
    public R deleteRole(@PathVariable Integer roleId){
        roleMenuService.deleteRoleMenu(roleId);
        roleService.deleteRole(roleId);
        return R.ok();
    }

}
