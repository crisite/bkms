package com.puff.bkms.model.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/30 上午8:34
 */
@Data
@Builder
public class OperationLog implements Serializable {

    private static final long serialVersionUID=1L;

    private int id;

    private int userId;

    private int operationType;

    private String content;

    private Date operationTime;
}
