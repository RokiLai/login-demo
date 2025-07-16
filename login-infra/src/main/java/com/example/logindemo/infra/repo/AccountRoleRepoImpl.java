package com.example.logindemo.infra.repo;

import com.example.logindemo.domain.repo.AccountRoleRepo;
import com.example.logindemo.infra.mapper.AccountRoleMapper;
import com.example.logindemo.infra.po.AccountRolePO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleRepoImpl implements AccountRoleRepo {
    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Override
    public List<Long> findRoleIdsByAccountId(Long accountId) {
        List<AccountRolePO> po = accountRoleMapper.selectByAccountId(accountId);
        return po.stream().map(AccountRolePO::getRoleId).toList();
    }
}
