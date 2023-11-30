package com.puff.bkms.handler;

import com.alibaba.fastjson.JSON;
import com.puff.bkms.annotation.OptLog;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.constant.RedisPrefixConst;
import com.puff.bkms.mapper.UserMapper;
import com.puff.bkms.model.dto.user.UserDetail;
import com.puff.bkms.model.enums.OperationEnum;
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
 * 这个处理器就是成功登录的最后一步，
 * 在这个处理器中更新一下用户登录成功后需要处理的事情： 更新用户信息（ip,time....） 处理返回信息 等
*
* @author: Puff
* @date: 2023/11/20 上午4:15
*/

@Component
@Slf4j
@OptLog(OperationEnum.LOGIN_TYPE)
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisClientUtils redisClientUtils;

    @Override
    // 此處加上@optLog後自定義Handler就不生效了
    // @OptLog(OperationEnum.LOGIN_TYPE)
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info(LOG_PRE+"AuthenticationSuccessHandlerImpl");

        updateUser(request);

        // 封装Token
        String username = UserUtils.getLoginUser().getUsername();
        String token = JwtUtil.createJWT(username);
        // Token存入redis
        redisClientUtils.set(RedisPrefixConst.BACKSTAGE_LOGIN_TOKEN+username,token,10*60);
        response.setContentType(APPLICATION_JSON);
        // 将token返回给前端
        response.getWriter().write(JSON.toJSONString(ResultUtils.success(token)));
    }

    // 认证成功以后先更新一下用户信息 bkms只需要更新last_login即可
    public void updateUser(HttpServletRequest request) {
        userMapper.updateLoginTimeById(UserUtils.getLoginUserId(), new Date());
    }
}