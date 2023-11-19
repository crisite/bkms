package com.puff.bkms.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/16 下午2:55
 */

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Date createTime;

    private Date lastLogin;

}
