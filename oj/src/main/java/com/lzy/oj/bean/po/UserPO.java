package com.lzy.oj.bean.po;

import com.baomidou.mybatisplus.annotation.*;
import com.lzy.oj.bean.entity.User;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserPO {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("account")
    private String account;

    @TableField("password")
    private String password;

    @TableField("user_name")
    private String userName;

    @TableField("avatar")
    private String avatar;

    @TableField("profile")
    private String profile;

    @TableField("role")
    private String role;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_delete")
    private Integer isDelete;

    public static UserPO user2PO(User user){
        UserPO userPO = new UserPO();
        userPO.setId(user.getId());
        userPO.setAccount(user.getAccount());
        userPO.setPassword(user.getPassword());
        userPO.setUserName(user.getUserName());
        userPO.setAvatar(user.getAvatar());
        userPO.setProfile(user.getProfile());
        userPO.setRole(user.getRole());
        userPO.setCreateTime(user.getCreateTime());
        userPO.setUpdateTime(user.getUpdateTime());
        userPO.setIsDelete(user.getIsDelete());
        return userPO;
    }

    public static User po2User(UserPO userPO){
        User user = new User();
        user.setId(userPO.getId());
        user.setAccount(userPO.getAccount());
        user.setPassword(userPO.getPassword());
        user.setUserName(userPO.getUserName());
        user.setAvatar(userPO.getAvatar());
        user.setProfile(userPO.getProfile());
        user.setRole(userPO.getRole());
        user.setCreateTime(userPO.getCreateTime());
        user.setUpdateTime(userPO.getUpdateTime());
        user.setIsDelete(userPO.getIsDelete());
        return user;
    }
}
