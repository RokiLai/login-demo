package com.example.logindemo.service.impl;

import com.example.logindemo.service.AccountService;
import com.example.logindemo.service.dto.UserLoginResult;
import com.example.logindemo.domain.model.Account;
import com.example.logindemo.domain.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepository;

    @Override
    public UserLoginResult login(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        if (account == null || !account.matchPassword(password)) {
            return null;
        }

        UserLoginResult result = new UserLoginResult();
        result.setId(account.getId());
        result.setUsername(account.getUsername());
        result.setEmail(account.getEmail()); // 设置 email
        return result;
    }

    @Override
    public boolean validatePassword(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        return account != null && account.matchPassword(password);
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            return false;
        }
        account.updatePassword(newPassword);
        accountRepository.save(account);
        return true;
    }
}
