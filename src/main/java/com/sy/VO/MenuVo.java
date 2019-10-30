package com.sy.VO;

import com.sy.pojo.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ssssyy
 * 2019/10/29 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo{
    private Integer menuId;
    private Integer parentId;
    private String name;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;
    private List<Menu> list;
}
