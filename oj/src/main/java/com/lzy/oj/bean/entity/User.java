package com.lzy.oj.bean.entity;


import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String account;

    private String password;

    private String userName;

    private String avatar;

    private String profile;

    private String role;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}
