package com.sy.service;

import com.sy.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * ssssyy
 * 2019/10/29 11:05
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public Integer addUserRole(Integer uid, Integer rid) {
        return userRoleDao.addUserRole(uid, rid);
    }

    @Override
    public ArrayList<Integer> findRolesByUserId(Integer userId) {
        return userRoleDao.findRolesByUserId(userId);
    }

    @Override
    public Integer deleteUserRole(Integer userId) {
        return userRoleDao.deleteUserRole(userId);
    }
}
