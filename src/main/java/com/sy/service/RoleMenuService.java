package com.sy.service;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * ssssyy
 * 2019/10/30 14:54
 */
public interface RoleMenuService {
    Integer addRoleMenu(Integer roleId,Integer menuId);
    Set<Integer> findMenusByRoleId(Integer roleId);
    Integer deleteRoleMenu(@Param("roleId")Integer roleId);
}
