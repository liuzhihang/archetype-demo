package com.liuzhihang.archetype.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户名
 * @author liuzhihang
 * @date 2020/7/15 21:25
 */
@Data
public class UserInfo implements Serializable {


    private String userId;
    private String phoneNumber;
    private String userName;
}
