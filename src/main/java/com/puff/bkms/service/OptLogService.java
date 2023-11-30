package com.puff.bkms.service;

import com.puff.bkms.model.dto.OperationLog.OperationLogDTO;
import com.puff.bkms.model.dto.OperationLog.OptLogQueryRequest;
import com.puff.bkms.model.entity.OperationLog;

import java.util.List;

public interface OptLogService {
    void insertLog(OperationLogDTO operationLogDTO);

    List<OperationLog> queryLogByUserId(OptLogQueryRequest optLogQueryRequest);
}
