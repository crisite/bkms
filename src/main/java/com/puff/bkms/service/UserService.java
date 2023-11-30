package com.puff.bkms.service;

import com.puff.bkms.model.dto.user.UserRegisterRequest;

/**
 * @author: Puff
 * @date: 2023/11/19 上午8:34
 */
public interface UserService {

    void registerUser(UserRegisterRequest userRegisterRequest);
}
