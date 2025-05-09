package com.lzy.oj.bean.dto.user;

import com.lzy.oj.bean.entity.User;
import lombok.Data;

@Data
public class LoginResponseDTO {

    private String token;

    private User user;
}
