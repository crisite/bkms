package com.puff.bkms.mapper;

import com.puff.bkms.model.dto.user.UserRegisterRequest;
import com.puff.bkms.model.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author: Puff
 * @date: 2023/11/19 上午8:16
 */
@Mapper
public interface UserAuthMapper {
    void insertUser(UserRegisterRequest userRegisterRequest);

    boolean isUserExists(String username);

    List<String> selectPowerByUserId(int id);

    void loginSuccess(UserInfo userInfo);
}
