package com.puff.bkms.service.impl;

import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.exception.ThrowUtils;
import com.puff.bkms.mapper.UserMapper;
import com.puff.bkms.model.dto.user.UserDetail;
import com.puff.bkms.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.puff.bkms.constant.CommonConst.LOG_PRE;

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
    UserMapper userMapper;


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
        User user = userMapper.selectUser(username);
        ThrowUtils.throwIf(user == null, ErrorCode.PARAMS_ERROR, "用户名或密码错误");

        // 获取用户角色
        List<String> roleList = userMapper.selectRoleByUserId(user.getId());
        // 获取权限信息
        List<String> powerList = userMapper.selectPowerByUserId(user.getId());

        // 权限列表赋给Spring Security使用的authList
        ArrayList<GrantedAuthority> authList = new ArrayList<>();
        powerList.forEach(power -> {
            authList.add(new SimpleGrantedAuthority(power));
        });

        UserDetail userDetail = UserDetail.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roleList(roleList)
                .powerList(powerList)
                .lastLogin(user.getLastLogin())
                .authList(authList)
                .build();
        log.info(LOG_PRE+userDetail);
        return userDetail;
    }

}
