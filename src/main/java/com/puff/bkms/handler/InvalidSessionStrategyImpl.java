package com.puff.bkms.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 应该是Session失效后调用执行InvalidSessionStrategy
 *
 * @author: Puff
 * @date: 2023/11/27 上午9:34
 */
@Slf4j
public class InvalidSessionStrategyImpl implements InvalidSessionStrategy {
    private  static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("puff_log -> InvalidSessionStrategyImpl");
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        String contextPath = request.getContextPath();
        String c= contextPath.length() > 0 ? contextPath : "/";
        cookie.setPath(c);
        response.addCookie(cookie);
        // 当认证失败后，响应 JSON 数据给前端
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString("策略失效"));
    }
}