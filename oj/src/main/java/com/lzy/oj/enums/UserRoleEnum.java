package com.lzy.oj.enums;

import com.lzy.oj.constant.UserRoleConstant;

public enum UserRoleEnum {

    USER(1, UserRoleConstant.USER),
    ADMIN(2,UserRoleConstant.ADMIN);

    private Integer level;
    private String role;

    UserRoleEnum(int level, String role) {
        this.level = level;
        this.role = role;
    }

    public static UserRoleEnum getEnumByRole(String role){
        for(UserRoleEnum roleEnum : UserRoleEnum.values()){
            if(roleEnum.role.equals(role)){
                return roleEnum;
            }
        }
        return null;
    }

    public Integer getLevel() {
        return level;
    }

    public String getRole() {
        return role;
    }
}
