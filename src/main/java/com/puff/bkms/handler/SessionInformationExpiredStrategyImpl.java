package com.puff.bkms.handler;

import com.alibaba.fastjson.JSON;
import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.constant.CommonConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * session超时策略实现
 * 当每个用户在系统中的最大session数达到后就会执行expiredSessionStrategy
 * 但是为什么session数量上限调用的是过期策略o.O?
 *
 * @author: Puff
 * @date: 2023/11/26 下午10:34
 */

@Component
@Slf4j
public class SessionInformationExpiredStrategyImpl implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        log.info("puff_log -> session 超时策略");
        event.getResponse().setContentType(CommonConst.APPLICATION_JSON);
        event.getResponse().getWriter().write(JSON.toJSONString(ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR,"Session过期，可能是被迫下线-.-")));
    }
}
