package com.lzy.oj.bean.dto.user;

import lombok.Data;

@Data
public class RegisterDTO {

    private String account;

    private String password;

    private String checkPassword;

    public boolean validate(){
        if(account==null||account.equals("")
                ||password==null||password.equals("")
                ||checkPassword==null||checkPassword.equals("")){
            return false;
        }
        return true;
    }
}
