package com.puff.bkms.handler;

import com.alibaba.fastjson.JSON;
import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.constant.CommonConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.puff.bkms.constant.CommonConst.LOG_PRE;

/**
 * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 * 使用自定义AuthenticationEntryPoint之后 spring security的默认login页面404了
 * so -> 现在AuthenticationEntryPointImpl先不使用了
 *
 * @author:     Puff
 * @date:    2023/11/26 下午10:05
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info(LOG_PRE+"AuthenticationEntryPointImpl");
        response.setContentType(CommonConst.APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR)));
    }
}