package com.puff.bkms.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Puff
 * @date: 2023/11/16 下午3:16
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String username;

    private String password;

    private String email;
}
