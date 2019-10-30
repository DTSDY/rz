package com.sy.dao;

import com.sy.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 20:28
 */
public interface RoleDao {
    List<Role> findAllRole();
    List<Role> findAllRoles(@Param("order") String order);
    Role findRoleByRoleName(@Param("roleName")String roleName);
    Integer addRole(Role role);

    Role findRoleByRoleId(@Param("roleId")Integer roleId);
    Integer updateRole(Role role);
    Integer deleteRole(Integer roleId);
}
