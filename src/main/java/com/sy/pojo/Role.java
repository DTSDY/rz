package com.sy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ssssyy
 * 2019/10/29 10:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer roleId;
    private String roleName;
    private String remark;
    private Integer createUserId;
    private Date createTime;
}
