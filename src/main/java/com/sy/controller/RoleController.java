package com.sy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.VO.MenuVo;
import com.sy.pojo.Role;
import com.sy.service.MenuService;
import com.sy.service.RoleService;
import com.sy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
