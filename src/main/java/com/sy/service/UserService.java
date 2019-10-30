package com.sy.service;

import com.sy.VO.UserVo2;
import com.sy.pojo.User;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 11:05
 */
public interface UserService {
    Integer addUser(User user);
    User findUserByUsername(String username);
    List<User> findAllUser(String order);
    UserVo2 findUserByUserId(Integer userId);
    Integer updateUser(User user);
    Integer deleteUser(Integer userId);
}
