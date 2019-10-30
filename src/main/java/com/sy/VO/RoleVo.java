package com.sy.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * ssssyy
 * 2019/10/30 11:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private Integer roleId;
    private String roleName;
    private String remark;
    private Set<Integer> menus;
}
