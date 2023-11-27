package com.puff.bkms.handler;

import com.alibaba.fastjson.JSON;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.constant.RedisPrefixConst;
import com.puff.bkms.mapper.UserAuthMapper;
import com.puff.bkms.model.dto.user.UserDetail;
import com.puff.bkms.utils.JwtUtil;
import com.puff.bkms.utils.RedisClientUtils;
import com.puff.bkms.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.puff.bkms.constant.CommonConst.APPLICATION_JSON;
import static com.puff.bkms.constant.CommonConst.LOG_PRE;

/**
* 重写认证成功处理类
*
* @author: Puff
* @date: 2023/11/20 上午4:15
*/

@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    RedisClientUtils redisClientUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info(LOG_PRE+"AuthenticationSuccessHandlerImpl");
        // 认证成功以后先更新一下用户信息 bkms只需要更新last_login即可
        UserDetail loginUser = UserUtils.getLoginUser();
        Date lastLogin = new Date();
        loginUser.setLastLogin(lastLogin);

        // 封装Token
        Integer userId = UserUtils.getLoginUserId();
        String token = JwtUtil.createJWT(Integer.toString(userId));
        // Token存入redis
        redisClientUtils.set(RedisPrefixConst.BACKSTAGE_LOGIN_TOKEN+userId,token,10*60);
        response.setContentType(APPLICATION_JSON);
        // 将token返回给前端
        response.getWriter().write(JSON.toJSONString(ResultUtils.success(token)));
    }
}