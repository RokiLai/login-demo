package com.example.logindemo.infra.converter;

import com.example.logindemo.domain.model.Account;
import com.example.logindemo.infra.po.AccountPO;

public class AccountConverter {
    public static Account toEntity(AccountPO po) {
        if (po == null)
            return null;
        // 修复：将 email 字段映射到 Account 对象
        return new Account(po.getId(), po.getUsername(), po.getPassword(), po.getEmail());
    }

    public static AccountPO toPO(Account entity) {
        if (entity == null)
            return null;
        AccountPO po = new AccountPO();
        po.setUsername(entity.getUsername());
        po.setPassword(entity.getPassword());
        po.setEmail(entity.getEmail()); // 设置 email 字段
        return po;
    }
}
