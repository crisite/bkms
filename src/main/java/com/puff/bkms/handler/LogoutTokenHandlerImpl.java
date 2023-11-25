<<<<<<< HEAD
package com.puff.bkms.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Puff
 * @date: 2023/11/20 上午7:18
 */
@Component
public class LogoutTokenHandlerImpl implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 删除redis中的token
        delLoginUserToken();
    }

    /**
     * 从redis中删除当前用户登录的token
     */
    private void delLoginUserToken(){
//        try {
//            Integer loginUserId = UserUtils.getLoginUserId();
//            redisService.del(RedisPrefixConst.BACKSTAGE_LOGIN_TOKEN+loginUserId);
//        } catch (Exception e) {
//            // 手动捕获异常，不然如果服务器重启后用户登出操作会报错
//            e.printStackTrace();
//        }
    }
=======
package com.puff.bkms.handler;/**
 * 
 *
 * @author:     Puff 
 * @date:    2023/11/20 上午7:18
 */ 
public class LogoutTokenHandlerImpl {
>>>>>>> a11844871c7a34f84365d33633d95fe997b7a65e
}
