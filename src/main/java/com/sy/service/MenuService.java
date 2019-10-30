package com.sy.service;

import com.sy.VO.MenuVo;
import com.sy.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 16:18
 */
public interface MenuService {
    List<MenuVo> findFatherMenuByUsername(@Param("username") String username);
//    Set<String> findPermsByUsernameAndFatherID(String username,Integer id);
    List<Menu> findMenusByFatherID(@Param("id") Integer id);
    List<String> findPermsByUsername(String username);
    List<MenuVo> findFirstMenu();
    List<Menu> findAllMenu(String order);


}
