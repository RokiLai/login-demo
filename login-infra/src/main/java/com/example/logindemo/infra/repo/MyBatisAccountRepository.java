package com.example.logindemo.infra.repo;

import com.example.logindemo.domain.model.Account;
import com.example.logindemo.domain.repo.AccountRepo;

import com.example.logindemo.infra.converter.AccountConverter;
import com.example.logindemo.infra.mapper.AccountMapper;
import com.example.logindemo.infra.po.AccountPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyBatisAccountRepository implements AccountRepo {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        AccountPO po = accountMapper.findByUsername(username);
        return AccountConverter.toEntity(po);
    }

    @Override
    public void save(Account account) {
        AccountPO po = AccountConverter.toPO(account);
        if (po.getId()==null) {
            accountMapper.insert(po);
        } else {
            accountMapper.update(po);   
        }
    }
}