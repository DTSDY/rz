package com.sy.service;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * ssssyy
 * 2019/10/29 22:08
 */
public interface UserRoleService {
    Integer addUserRole(Integer uid,Integer rid);
    ArrayList<Integer> findRolesByUserId(Integer userId);
    Integer deleteUserRole(@Param("userId")Integer userId);


}
