package com.puff.bkms.service.impl;

import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.exception.ThrowUtils;
import com.puff.bkms.mapper.UserAuthMapper;
import com.puff.bkms.mapper.UserInfoMapper;
import com.puff.bkms.model.dto.user.UserDetail;
import com.puff.bkms.model.entity.UserInfo;
import com.puff.bkms.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户身份校验
 *
 * @author: Puff
 * @date: 2023/11/19 上午8:06
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    UserInfoMapper userInfoMapper;


    /**
     *  用户登录接口
     *  loadUserByUsername方法是在校验登录后封装并一个UserDetails
     *  登录校验要在拦截器里面处理
     *
     * @param username:
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.selectUserInfo(username);
        ThrowUtils.throwIf(userInfo == null, ErrorCode.PARAMS_ERROR, "用户名或密码错误");

        // 获取用户角色
        List<String> roleList = userInfoMapper.selectRoleByUserId(userInfo.getId());
        // 获取权限信息
        List<String> powerList = userAuthMapper.selectPowerByUserId(userInfo.getId());

        Date lastLogin = new Date();
        userInfo.setLastLogin(lastLogin);
        userAuthMapper.loginSuccess(userInfo);

        return UserDetail.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .roleList(roleList)
                .powerList(powerList)
                .last_login(lastLogin)
                .build();
    }

}
