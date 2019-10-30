package com.sy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ssssyy
 * 2019/10/29 10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer status;
    private Integer createUserId;
    private Date createTime;
    private Integer deptId;
    private String sex;
    private Date lockdate;
    private String salt;
}
