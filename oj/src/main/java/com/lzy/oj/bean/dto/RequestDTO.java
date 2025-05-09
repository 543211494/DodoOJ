package com.lzy.oj.bean.dto;

import com.lzy.oj.bean.entity.User;
import lombok.Data;

@Data
public class RequestDTO {

    private String token;

    private User user;
}
