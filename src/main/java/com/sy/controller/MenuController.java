package com.sy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.pojo.Menu;
import com.sy.service.MenuService;
import com.sy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ssssyy
 * 2019/10/30 16:09
 */
@Controller
@RequestMapping("/sys")
@CrossOrigin(value = "http://localhost:8081")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/list")
    @ResponseBody
    public R menuList(String order, Integer limit, Integer offset) {
        offset = offset / 3 + 1;
        PageHelper.startPage(offset, limit);
        List<Menu> allMenus = menuService.findAllMenu(order);
        PageInfo<Menu> pageInfo = new PageInfo<>(allMenus);
        return R.ok().put("total", pageInfo.getTotal()).put("rows", pageInfo.getList());
    }
}
