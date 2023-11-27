package com.puff.bkms.utils;

import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.exception.BusinessException;
import com.puff.bkms.model.dto.user.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author: Puff
 * @date: 2023/11/28 上午3:37
 */
public class UserUtils {

    // 获取当前登录用户信息
    public static UserDetail getLoginUser(){
        try {
            return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "请先登录再操作");
        }
    }

    // 判断用户是否登录
    public static Boolean isLogin(){
        try {
            UserDetail userDetailDTO = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (Objects.isNull(userDetailDTO)){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // 获取登录用户id
    public static Integer getLoginUserId(){
        return getLoginUser().getId();
    }


}