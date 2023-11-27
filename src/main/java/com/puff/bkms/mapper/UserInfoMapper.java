package com.puff.bkms.mapper;

import com.puff.bkms.model.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    UserInfo selectUserInfo(String username);

    List<String> selectRoleByUserId(int id);

}
