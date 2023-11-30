package com.puff.bkms.mapper;

import com.puff.bkms.model.dto.user.UserRegisterRequest;
import com.puff.bkms.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(UserRegisterRequest userRegisterRequest);

    User selectUser(String username);

    void updateById(User user);

    void updateLoginTimeById(int id, Date lastLogin);

    List<String> selectPowerByUserId(int id);

    List<String> selectRoleByUserId(int id);

    boolean isUserExists(String username);
}
