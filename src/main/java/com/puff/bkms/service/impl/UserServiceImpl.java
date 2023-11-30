package com.puff.bkms.service.impl;

import com.puff.bkms.common.CommonUtils;
import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.exception.ThrowUtils;
import com.puff.bkms.mapper.UserMapper;
import com.puff.bkms.model.dto.user.UserRegisterRequest;
import com.puff.bkms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Puff
 * @date: 2023/11/19 上午8:34
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        String username = userRegisterRequest.getUsername();
        String email = userRegisterRequest.getEmail();

        // 校验注册信息 邮箱合法性、用户名唯一
        ThrowUtils.throwIf(!CommonUtils.checkEmail(email), ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(userMapper.isUserExists(username), ErrorCode.PARAMS_ERROR);

        String password = passwordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(password);
        userMapper.insertUser(userRegisterRequest);
    }
}
