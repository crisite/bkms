package com.puff.bkms.model.dto.OperationLog;

import com.puff.bkms.common.PageRequest;
import lombok.Data;

/**
 * @author: Puff
 * @date: 2023/11/30 上午8:49
 */
@Data
public class OptLogQueryRequest extends PageRequest {
    // 日志查询应该只需要支持根据userId分页查询就行了
    int userId;
}
