package com.sy.VO;

import com.sy.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ssssyy
 * 2019/10/29 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {
    private String captcha;
}
