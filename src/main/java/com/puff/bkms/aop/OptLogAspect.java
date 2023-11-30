package com.puff.bkms.aop;

import com.puff.bkms.annotation.OptLog;
import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.exception.BusinessException;
import com.puff.bkms.model.dto.OperationLog.OperationLogDTO;
import com.puff.bkms.service.OptLogService;
import com.puff.bkms.utils.UserUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志aop
 * 关于图书日志还有一些不清楚的，insert和del图书请求参数都不相同，
 * 按理来说只需要存一个bookId即可，但是insert的请求参数是bookAddRequest，难不成还需要if单独判断吗
 *
 * @author: Puff
 * @date: 2023/11/29 上午9:27
 */
@Aspect
@Slf4j
@Component
public class OptLogAspect {

    @Autowired
    private OptLogService optLogService;

    /**
     * 切入点声明,指定注解，在标记了注解的位置进行切入
     */
    @Pointcut("@annotation(com.puff.bkms.annotation.OptLog)")
    public void optLogPointCut(){}

    /**
     * @param joinPoint:
     * @return void
     */
    @Around("optLogPointCut()")
    public void saveOptLog(JoinPoint joinPoint){
        /**
         * 此处获得userId或许有个问题 调用logout方法应该没有userId了
         * 我的选择是将注解添加到LogoutTokenHandlerImpl（在LogoutSuccessHandler之前执行）
         * */
        Integer userId = UserUtils.getLoginUserId();
        // 获取封装了署名信息的对象,在该对象中可以获取到原始方法的各种属性
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切点方法
        Method method = signature.getMethod();
        // 获取切点方法对应的注解
        OptLog optLogAnnotation = method.getAnnotation(OptLog.class);
        int optType = optLogAnnotation.value().getCode();
        String optContent = optLogAnnotation.value().getValue();
        System.out.println(new Date());
        optLogService.insertLog(new OperationLogDTO(userId, optType, optContent, new Date()));
    }

}
