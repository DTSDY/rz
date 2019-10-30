package com.sy.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * ssssyy
 * 2019/10/30 9:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo2{
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer status;
    private String sex;
    private ArrayList<Integer> roles;
}
