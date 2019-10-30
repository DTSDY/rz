package com.sy.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * ssssyy
 * 2019/10/30 12:14
 */
public interface RoleMenuDao {
    Integer addRoleMenu(@Param("roelId")Integer roleId,@Param("MenuId")Integer menuId);
    Integer deleteRoleMenu(@Param("roleId")Integer roleId);

    Set<Integer> findMenusByRoleId(@Param("roleId")Integer roleId);
}
