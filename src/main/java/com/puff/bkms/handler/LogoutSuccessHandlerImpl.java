package com.puff.bkms.handler;

<<<<<<< HEAD
import com.alibaba.fastjson.JSON;
import com.puff.bkms.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
=======
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
>>>>>>> a11844871c7a34f84365d33633d95fe997b7a65e

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
<<<<<<< HEAD
import static com.puff.bkms.constant.CommonConst.APPLICATION_JSON;
/**
 * 登录成功处理器
 *
 * @author: Puff
 * @date: 2023/11/20 上午7:16
 */
@Component
@Slf4j
=======

/**
 * @author: Puff
 * @date: 2023/11/20 上午7:16
 */
>>>>>>> a11844871c7a34f84365d33633d95fe997b7a65e
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON);
<<<<<<< HEAD
        response.getWriter().write(JSON.toJSONString(ResultUtils.success(null,"puff response")));
        log.info("puff_log -- LogoutSuccessHandlerImpl");
=======
        response.getWriter().write(JSON.toJSONString(ResponseResult.ok()));
>>>>>>> a11844871c7a34f84365d33633d95fe997b7a65e
    }
}
