package com.sy.service;

import com.sy.VO.UserVo2;
import com.sy.dao.UserDao;
import com.sy.dao.UserRoleDao;
import com.sy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ssssyy
 * 2019/10/29 11:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

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

    @Override
    public UserVo2 findUserByUserId(Integer userId) {
        User user = userDao.findUserByUserId(userId);
        UserVo2 userVo2 = new UserVo2(user.getUserId(),user.getUsername(),null,user.getEmail(),user.getMobile(),user.getStatus(),user.getSex(),null);
        ArrayList<Integer> roles = userRoleDao.findRolesByUserId(user.getUserId());
        userVo2.setRoles(roles);
        System.out.println(userVo2);
        return userVo2;

    }
}
