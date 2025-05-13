package com.lzy.oj.bean.dto.user;

import com.lzy.oj.bean.dto.RequestDTO;
import lombok.Data;

@Data
public class UpdateDTO extends RequestDTO {

    private String userName;

    private String email;

    public boolean validate(){
        if(userName==null||userName.equals("")||email==null||email.equals("")){
            return false;
        }
        return true;
    }
}
