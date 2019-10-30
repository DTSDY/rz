package com.sy.service;

import com.sy.VO.MenuVo;
import com.sy.dao.MenuDao;
import com.sy.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 16:21
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuVo> findFatherMenuByUsername(String username) {
        List<MenuVo> fatherMenu = menuDao.findFatherMenuByUsername(username);
        for (MenuVo menu : fatherMenu) {
            List<Menu> menus = menuDao.findMenusByFatherID(menu.getMenuId());
            menu.setList(menus);
        }
        return fatherMenu;
    }

    @Override
    public List<Menu> findMenusByFatherID(Integer id) {
        return menuDao.findMenusByFatherID(id);
    }

    @Override
    public List<String> findPermsByUsername(String username) {
        return menuDao.findPermsByUsername(username);
    }

    @Override
    public List<MenuVo> findFirstMenu() {
        return menuDao.findFirstMenu();
    }

}
