package com.puff.bkms.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/19 下午8:08
 */
public class Power implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer id;

    private String perms;

    private Date createTime;

    private Date updateTime;
}
