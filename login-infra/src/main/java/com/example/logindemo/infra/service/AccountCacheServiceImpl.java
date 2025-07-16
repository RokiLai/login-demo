package com.example.logindemo.infra.service;

import com.example.logindemo.auth.AccountInfo;
import com.example.logindemo.domain.service.AccountCacheService;
import com.example.logindemo.infra.reids.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCacheServiceImpl implements AccountCacheService {

    private final String PREFIX_ACCOUNT_INFO = "login:account_info:";

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void saveAccountInfo(AccountInfo accountInfo) {
        redisUtil.set(PREFIX_ACCOUNT_INFO + accountInfo.getUsername(), accountInfo);
    }

    @Override
    public AccountInfo getAccountInfo(String username) {
        return (AccountInfo) redisUtil.get(PREFIX_ACCOUNT_INFO + username);
    }

    @Override
    public void deleteAccountInfo(String username) {
        redisUtil.delete(PREFIX_ACCOUNT_INFO + username);
    }
}
