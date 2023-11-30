package com.puff.bkms.annotation;

import com.puff.bkms.model.enums.OperationEnum;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLog {
    OperationEnum value();
}
