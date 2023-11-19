package com.puff.bkms.mapper;

import com.puff.bkms.model.dto.user.UserRegisterRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Puff
 * @date: 2023/11/19 上午8:16
 */
@Mapper
public interface UserAuthMapper {
    void insertUser(UserRegisterRequest userRegisterRequest);

    boolean isUserExists(String username);
}
