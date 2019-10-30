package com.sy.dao;

import com.sy.VO.MenuVo;
import com.sy.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 15:53
 */
public interface MenuDao {
    List<MenuVo> findFatherMenuByUsername(@Param("username") String username);
    List<Menu> findMenusByFatherID(@Param("id") Integer id);
    List<String> findPermsByUsername(@Param("username")String username);

    List<MenuVo> findFirstMenu();
}
