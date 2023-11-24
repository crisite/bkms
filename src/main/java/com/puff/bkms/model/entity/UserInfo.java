package com.puff.bkms.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/20 上午5:04
 */
@Data
public class UserInfo {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Date createTime;

    private Date lastLogin;

}
