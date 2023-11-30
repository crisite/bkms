package com.puff.bkms.mapper;

import com.puff.bkms.model.dto.OperationLog.OperationLogDTO;
import com.puff.bkms.model.dto.OperationLog.OptLogQueryRequest;
import com.puff.bkms.model.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationLogMapper {
    void insertLog(OperationLogDTO operationLogDTO);

    List<OperationLog> queryLogByUserId(OptLogQueryRequest optLogQueryRequest);
}
