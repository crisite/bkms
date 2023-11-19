package com.puff.bkms.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 通用工具类
 *
 * @author: Puff
 * @date: 2023/11/19 上午8:50
 */
public class CommonUtils {
    /**
     * 检测邮箱是否合法
     *
     * @param email
     * @return 合法状态
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isBlank(email)){
            return false;
        }
        // 正则表达式
        String rule = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        // 邮箱的正则表达式
        return email.matches(rule);
    }
}
