package com.sy.service;

import com.sy.dao.RoleDao;
import com.sy.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 20:30
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }
}
