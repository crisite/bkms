package com.puff.bkms.handler;

import com.puff.bkms.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 重写认证成功处理类
*
* @author: Puff
* @date: 2023/11/20 上午4:15
*/

@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    log.info("puff_log --> AuthenticationSuccessHandlerImpl");
    // 后台登录将用户id封装成token返回给前端
//        Integer userId = UserUtils.getLoginUserId();```
//        // token对用户的id进行加密
//        String token = JwtUtil.createJWT(Integer.toString(userId));
//        // 将token存到redis中
//        redisService.set(RedisPrefixConst.BACKSTAGE_LOGIN_TOKEN+userId,token,6*60*60);
//        httpServletResponse.setContentType(APPLICATION_JSON);
//        // 将token返回给前端
//        httpServletResponse.getWriter().write(JSON.toJSONString(ResponseResult.ok(token)));
}
}
