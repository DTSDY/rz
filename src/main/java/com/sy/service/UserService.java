package com.sy.service;

import com.sy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 11:05
 */
public interface UserService {
    Integer addUser(User user);
    User findUserByUsername(String username);
    List<User> findAllUser(@Param("order")String order);

}
