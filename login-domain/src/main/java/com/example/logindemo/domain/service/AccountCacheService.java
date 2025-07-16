package com.example.logindemo.domain.service;

import com.example.logindemo.auth.AccountInfo;

public interface AccountCacheService {

    void saveAccountInfo(AccountInfo accountInfo);

    AccountInfo getAccountInfo(String username);

    void deleteAccountInfo(String username);

}
