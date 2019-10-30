package com.sy.dao;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * ssssyy
 * 2019/10/29 21:51
 */
public interface UserRoleDao {
    Integer addUserRole(@Param("uid") Integer uid,@Param("rid") Integer rid);
    ArrayList<Integer> findRolesByUserId(@Param("userId")Integer userId);
}
