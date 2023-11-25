<<<<<<< HEAD
package com.puff.bkms.mapper;

import com.puff.bkms.model.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    UserInfo selectUserInfo(String username);

    List<String> selectRoleByUserId(int id);
=======
package com.puff.bkms.mapper;public interface UserInfoMapper {
>>>>>>> a11844871c7a34f84365d33633d95fe997b7a65e
}
