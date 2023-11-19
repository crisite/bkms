package com.puff.bkms.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户身份校验
 *
 * @author: Puff
 * @date: 2023/11/19 上午8:06
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {



    /**
     *  用户登录接口
     *
     * @param username:
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
