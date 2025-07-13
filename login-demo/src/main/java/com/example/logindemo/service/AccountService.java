package com.example.logindemo.service;

import com.example.logindemo.service.dto.UserLoginResult;

public interface AccountService {

    UserLoginResult login(String username, String password);

}