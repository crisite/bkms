package com.puff.bkms.handler;


import com.alibaba.fastjson.JSON;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.puff.bkms.constant.CommonConst.APPLICATION_JSON;
import static com.puff.bkms.constant.CommonConst.LOG_PRE;

/**
 * 登录成功处理器
 *
 * @author: Puff
 * @date: 2023/11/20 上午7:16
 */
@Component
@Slf4j


/**
 * @author: Puff
 * @date: 2023/11/20 上午7:16
 */
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 用户退出后将 SecurityContext 卸载掉
        SecurityContextHolder.clearContext();
        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(ResultUtils.success(null,"logout success")));
        log.info(LOG_PRE+"LogoutSuccessHandlerImpl");
    }
}
