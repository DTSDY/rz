package com.sy.dao;

import com.sy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 11:01
 */
public interface UserDao {
    Integer addUser(User user);
    User findUserByUserId(@Param("userId")Integer userId);
    User findUserByUsername(@Param("username")String username);
    List<User> findAllUser(@Param("order")String order);
    Integer updateUser(User user);
    Integer deleteUser(@Param("userId") Integer userId);
}
