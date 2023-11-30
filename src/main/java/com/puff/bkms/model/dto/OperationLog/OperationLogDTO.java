package com.puff.bkms.model.dto.OperationLog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/30 上午8:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogDTO {
    private int userId;

    private int operationType;

    private String content;

    private Date operationTime;
}