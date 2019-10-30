package com.sy.service;

import com.sy.dao.UserDao;
import com.sy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 11:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public List<User> findAllUser(String order) {
        return userDao.findAllUser(order);
    }
}
