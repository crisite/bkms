package com.puff.bkms.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Puff
 * @date: 2023/11/29 上午8:49
 */
public enum OperationEnum {

    REGISTER_TYPE(0,"用户注册"),
    LOGIN_TYPE(1,"用户登录"),
    LOGOUT_TYPE(2,"用户退出登录"),
    LEND_TYPE(3,"借书"),
    REPAY_TYPE(4,"还书"),
    UPDATE_TYPE(5,"修改书籍数据"),
    INSERT_TYPE(6,"添加书籍"),
    DELETE_TYPE(7,"删除书籍");


    private final int code;

    private final String value;

    OperationEnum(int code, String value) {
        this.code  = code;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getTypes() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 type 获取枚举
     *
     * @param value
     * @return
     */
    public static OperationEnum getEnumByType(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (OperationEnum anEnum : OperationEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}
