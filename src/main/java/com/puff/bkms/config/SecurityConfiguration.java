package com.puff.bkms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author: Puff
 * @date: 2023/11/19 上午5:19
 */

@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * 创建一个BCryptPasswordEncoding注入容器,设置密码验证方法
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 密码验证默认使用BCryptPassword加密
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests((authz) -> authz
//        .anyRequest().authenticated()
//        )
//                .httpBasic(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
}
