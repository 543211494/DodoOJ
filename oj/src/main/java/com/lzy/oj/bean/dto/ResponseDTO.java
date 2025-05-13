package com.lzy.oj.bean.dto;

import com.lzy.oj.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    /**
     * 是否请求成功
     * true-成功
     * false-失败
     */
    private Boolean success;
    /**
     * 返回的数据，具体类型见接口
     */
    private T data;
    /**
     * 状态说明
     * 成功时为空
     * 失败时为错误信息
     */
    private String message;

    private String code;

    public static final <T> ResponseDTO success(T data) {
        return new ResponseDTO(true, data, "",null);
    }

    public static final <T> ResponseDTO fail(String message) {
        String code = String.valueOf(ErrorEnum.getEnumByMessage(message).getCode());
        return new ResponseDTO(false, null,message,code);
    }
}
