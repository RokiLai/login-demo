package com.example.logindemo.domain.repo;

import com.example.logindemo.domain.model.Account;

public interface AccountRepo {

    Account findByUsername(String username);

    void save(Account account);

    void updateAccountRole(Account account);
}