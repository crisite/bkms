package com.puff.bkms.config;

import com.puff.bkms.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * SpringSecurity 安全配置类
 *
 * @author: Puff
 * @date: 2023/11/19 上午5:19
 */

@EnableWebSecurity
@Configuration
// 开启全局权限认证 @PreAuthorize才可以使用
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{
    private final String LOGIN_URI = "/login";
    // 登录成功处理器
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    // 登录失败处理器
    @Autowired
    private AuthenticationFailHandlerImpl authenticationFailHandler;
    // 登出成功处理器
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    // 登出处理类
    @Autowired
    private LogoutTokenHandlerImpl logoutHandler;
    // 权限不足
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    // 用户未登录处理
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private SessionInformationExpiredStrategyImpl sessionInformationExpiredStrategy;
    @Autowired
    private InvalidSessionStrategyImpl invalidSessionStrategy;
    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 创建一个BCryptPasswordEncoding注入容器,设置密码验证方法
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 密码验证默认使用BCryptPassword加密
    }


    // 防止用户重复登录,监听session
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 配置登录注销路径
        http.formLogin()
                .loginProcessingUrl(LOGIN_URI)
                .successHandler(authenticationSuccessHandler)   // 登录成功处理
                .failureHandler(authenticationFailHandler)      // 登录失败处理
                // 使用了自定义的authenticationSuccessHandler口不能再设置defaultSuccessUrl
                // .defaultSuccessUrl("/doc.html")
                .and()
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(logoutHandler)        // 登出处理
                .logoutSuccessHandler(logoutSuccessHandler);

        // session管理
        http.sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)     // session失效处理
                .maximumSessions(1) // 最大session数
                .maxSessionsPreventsLogin(false)  // 某用户达到最大会话并发数后，新会话请求会被拒绝登录
                .expiredSessionStrategy(sessionInformationExpiredStrategy) // 监听session消除后的处理
                .sessionRegistry(sessionRegistry);

        // 关闭csrf跨站防护
             http.csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler); // 权限不足处理
//                .authenticationEntryPoint(authenticationEntryPoint); // 用户未登录处理

        // 路由权限配置
        http.authorizeRequests()
                // 放行所有接口
                .anyRequest().permitAll();
        return http.build();
    }
}
