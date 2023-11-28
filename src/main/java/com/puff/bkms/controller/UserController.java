package com.puff.bkms.controller;

import com.puff.bkms.common.BaseResponse;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.model.dto.user.UserRegisterRequest;
import com.puff.bkms.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户校验
 *
 * @author: Puff
 * @date: 2023/11/16 下午3:18
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/register")
    public BaseResponse<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        userAuthService.registerUser(userRegisterRequest);
        return ResultUtils.success(null, "register success");
    }

    @PostMapping("/test")
    public String test(@RequestBody UserRegisterRequest userRegisterRequest, HttpServletRequest request) {
        log.info(request.getHeader("Authorization"));
        return " ";
    }
}
