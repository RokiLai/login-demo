package com.example.logindemo.service;

import com.example.logindemo.service.dto.UserLoginResult;

public interface AccountService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果，包含用户信息
     */
    UserLoginResult login(String username, String password);
    /**
     * 校验用户名和密码是否匹配
     * @param username 用户名
     * @param oldPassword 旧密码
     * @return 是否匹配
     */
    boolean validatePassword(String username, String oldPassword);
    /**
     * 更新用户密码
     * @param username 用户名
     * @param newPassword 新密码
     */
    boolean updatePassword(String username, String newPassword);
}