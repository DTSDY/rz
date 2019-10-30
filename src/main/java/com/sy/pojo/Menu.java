package com.sy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ssssyy
 * 2019/10/29 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer menuId;
    private Integer parentId;
    private String name;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;
}
