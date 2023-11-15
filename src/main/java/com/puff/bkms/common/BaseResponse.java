package com.puff.bkms.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author: Puff
 * @date: 2023/11/14 下午8:19
 */
@Data
public class BaseResponse<T> implements Serializable {
    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
