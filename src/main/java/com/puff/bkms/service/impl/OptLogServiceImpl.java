package com.puff.bkms.service.impl;

import com.puff.bkms.mapper.OperationLogMapper;
import com.puff.bkms.model.dto.OperationLog.OperationLogDTO;
import com.puff.bkms.model.dto.OperationLog.OptLogQueryRequest;
import com.puff.bkms.model.entity.OperationLog;
import com.puff.bkms.service.OptLogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志接口
 *
 * @author: Puff
 * @date: 2023/11/30 上午8:57
 */
@Service
@Slf4j
public class OptLogServiceImpl implements OptLogService {
    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    @Async("poolExecutor")
    public void insertLog(OperationLogDTO operationLogDTO) {
        operationLogMapper.insertLog(operationLogDTO);
    }

    @Override
    public List<OperationLog> queryLogByUserId(OptLogQueryRequest optLogQueryRequest) {
        return operationLogMapper.queryLogByUserId(optLogQueryRequest);
    }
}
