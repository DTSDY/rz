package com.sy.service;

import com.sy.VO.RoleVo;
import com.sy.dao.RoleDao;
import com.sy.dao.RoleMenuDao;
import com.sy.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * ssssyy
 * 2019/10/29 20:30
 */
@Service
public class RoleServiceImpl implements RoleService {



    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public List<Role> findAllRoles(String order) {
        return roleDao.findAllRoles(order);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleDao.findRoleByRoleName(roleName);
    }

    @Override
    public Integer addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public RoleVo findRoleByRoleId(Integer roleId) {
        Role role = roleDao.findRoleByRoleId(roleId);
        RoleVo roleVo = new RoleVo(role.getRoleId(),role.getRoleName(),role.getRemark(),null);
        Set<Integer> menus = roleMenuDao.findMenusByRoleId(role.getRoleId());
        roleVo.setMenus(menus);
        return roleVo;
    }

    @Override
    public Integer updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        return roleDao.deleteRole(roleId);
    }
}
