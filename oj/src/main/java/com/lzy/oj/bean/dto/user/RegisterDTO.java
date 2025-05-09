package com.lzy.oj.bean.dto.user;

import lombok.Data;

@Data
public class RegisterDTO {

    private String account;

    private String password;

    private String checkPassword;
}
