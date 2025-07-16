package com.example.logindemo.controller;

import com.example.logindemo.annotation.PassToken;
import com.example.logindemo.controller.request.LoginRequest;
import com.example.logindemo.controller.request.RegisterRequest;
import com.example.logindemo.controller.request.UpdatePasswordRequest;
import com.example.logindemo.controller.response.CommonResponse;

import com.example.logindemo.domain.model.Account;
import com.example.logindemo.service.AccountService;
import com.example.logindemo.service.dto.UserLoginDTO;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    AccountService accountService;

    // 处理登录请求
    @PassToken
    @PostMapping("/login")
    public CommonResponse<UserLoginDTO> login(@RequestBody LoginRequest request,
                                              HttpServletResponse response) {

        // 登录校验（用户名 + 密码验证）
        UserLoginDTO userLoginDTO = accountService.login(request.getUsername(), request.getPassword());
        if (userLoginDTO == null) {
            throw new RuntimeException("Login failed");
        }
        // 设置响应头 Authorization
        response.setHeader("Authorization", userLoginDTO.getToken());
        response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 跨域时允许前端读取该头
        return CommonResponse.success(userLoginDTO);
    }

    @PassToken
    @PostMapping("/register")
    public CommonResponse<Boolean> register(@RequestBody RegisterRequest request) {
        accountService.register(request.getUsername(), request.getPassword(), request.getEmail(), null);
        return CommonResponse.success(true);
    }

    // 更新密码
    @PostMapping("/update-password")
    public CommonResponse<Boolean> updatePassword(@RequestBody UpdatePasswordRequest request) {
        // 更新密码
        accountService.updatePassword(request.getOldPassword(), request.getNewPassword());
        return CommonResponse.success(true);
    }

}
