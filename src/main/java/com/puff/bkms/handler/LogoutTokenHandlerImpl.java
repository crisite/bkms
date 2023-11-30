package com.puff.bkms.handler;

import com.puff.bkms.annotation.OptLog;
import com.puff.bkms.constant.RedisPrefixConst;
import com.puff.bkms.model.enums.OperationEnum;
import com.puff.bkms.utils.RedisClientUtils;
import com.puff.bkms.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.puff.bkms.constant.CommonConst.LOG_PRE;

/**
 * 用户注销删除Token
 * @author: Puff
 * @date: 2023/11/20 上午7:18
 */
@Component
@Slf4j
public class LogoutTokenHandlerImpl implements LogoutHandler {
    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    RedisClientUtils redisClientUtils;

    @Override
    @OptLog(OperationEnum.LOGOUT_TYPE)
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info(LOG_PRE+"LogoutTokenHandlerImpl");

        // 删除redis中的token
        delLoginUserToken();

        // 退出之后,将对应session从缓存中清除 SessionRegistryImpl.principals
        sessionRegistry.removeSessionInformation(request.getSession().getId());
    }

    /**
     * 从redis中删除当前用户登录的token
     */
    private void delLoginUserToken() {
        try {
            Integer loginUserId = UserUtils.getLoginUserId();
            redisClientUtils.del(RedisPrefixConst.BACKSTAGE_LOGIN_TOKEN+loginUserId);
        } catch (Exception e) {
            // 手动捕获异常，不然如果服务器重启后用户登出操作会报错
            e.printStackTrace();
        }
    }
}