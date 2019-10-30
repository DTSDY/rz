package com.sy.service;

import com.sy.VO.RoleVo;
import com.sy.pojo.Role;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 20:30
 */
public interface RoleService {
    List<Role> findAllRole();
    List<Role> findAllRoles(String order);
    Role findRoleByRoleName(String roleName);
    Integer addRole(Role role);
    RoleVo findRoleByRoleId(Integer roleId);
    Integer updateRole(Role role);
    Integer deleteRole(Integer roleId);
}
