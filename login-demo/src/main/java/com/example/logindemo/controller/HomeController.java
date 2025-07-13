package com.example.logindemo.controller;

import com.example.logindemo.annotation.PassToken;
import com.example.logindemo.controller.request.LoginRequest;
import com.example.logindemo.controller.response.CommonResponse;
import com.example.logindemo.controller.response.UserInfoResponse;
import com.example.logindemo.service.dto.UserLoginResult;
import com.example.logindemo.service.AccountService;
import com.example.logindemo.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class HomeController {
    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtil jwtUtil;

    // 处理登录请求
    @PassToken
    @PostMapping("/login")
    public CommonResponse<UserInfoResponse> login(@RequestBody LoginRequest request,
                                                  HttpServletResponse response) {

        // 登录校验（用户名 + 密码验证）
        UserLoginResult loginResult = accountService.login(request.getUsername(), request.getPassword());
        if (loginResult == null) {
            throw new RuntimeException("Login failed");
        }

        // 构造 UserInfoResponse，包含 email 字段
        UserInfoResponse userInfo = new UserInfoResponse();
        userInfo.setId(loginResult.getId());
        userInfo.setUsername(loginResult.getUsername());
        userInfo.setEmail(loginResult.getEmail()); // 设置 email

        // 生成 JWT
        String token = jwtUtil.generateToken(userInfo.getUsername());

        // 设置响应头 Authorization
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 跨域时允许前端读取该头

        return CommonResponse.success(userInfo);
    }

}
