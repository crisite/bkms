package com.puff.bkms.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.puff.bkms.constant.CommonConst.APPLICATION_JSON;
import static com.puff.bkms.constant.CommonConst.LOG_PRE;

/**
 * 应该是Session 过期后调用执行InvalidSessionStrategy
 *
 * @author: Puff
 * @date: 2023/11/27 上午9:34
 */
@Slf4j
public class InvalidSessionStrategyImpl implements InvalidSessionStrategy {
    private  static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info(LOG_PRE+"InvalidSessionStrategyImpl");
        // 当认证失败后，响应 JSON 数据给前端
        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(objectMapper.writeValueAsString("Session 过期后 策略失效"));
    }
}