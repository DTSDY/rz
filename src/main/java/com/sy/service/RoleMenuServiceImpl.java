package com.sy.service;

import com.sy.dao.RoleMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * ssssyy
 * 2019/10/29 11:05
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService{

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Integer addRoleMenu(Integer roleId, Integer menuId) {
        return roleMenuDao.addRoleMenu(roleId, menuId);
    }

    @Override
    public Set<Integer> findMenusByRoleId(Integer roleId) {
        return roleMenuDao.findMenusByRoleId(roleId);
    }

    @Override
    public Integer deleteRoleMenu(Integer roleId) {
        return roleMenuDao.deleteRoleMenu(roleId);
    }
}
