package com.puff.bkms.config;

import com.puff.bkms.handler.InvalidSessionStrategyImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.InvalidSessionStrategy;

/**
 * 自定义Session配置类 用来注册一些session策略的bean
 * 作用是解决bean的循环调用 o.O?
 *
 * @author: Puff
 * @date: 2023/11/27 上午9:36
 */
@Configuration
public class SessionConfig {
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategyImpl customInvalidSessionStrategy(){
        return  new InvalidSessionStrategyImpl();
    }

    // 注入SessionRegistry 获取在线用户信息
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}