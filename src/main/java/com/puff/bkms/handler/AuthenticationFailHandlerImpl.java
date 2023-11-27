package com.puff.bkms.handler;

import com.alibaba.fastjson.JSON;
import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.common.ResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.puff.bkms.constant.CommonConst.APPLICATION_JSON;

/**
 * @author: Puff
 * @date: 2023/11/20 上午7:04
 */
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    // 身份认证失败时调用
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultUtils.error(ErrorCode.PARAMS_ERROR,e.getMessage())));
    }
}