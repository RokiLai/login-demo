package com.example.logindemo.infra.converter;

import com.example.logindemo.domain.model.Account;
import com.example.logindemo.infra.po.AccountPO;
import com.example.logindemo.infra.po.AccountRolePO;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class AccountConverter {
    public static Account toEntity(AccountPO po, List<AccountRolePO> accountRolePOS) {
        if (po == null)
            return null;
        Account account = new Account(po.getId(), po.getUsername(), po.getPassword(), po.getEmail());
        if (!CollectionUtils.isEmpty(accountRolePOS)) {
            account.setRoleIds(accountRolePOS.stream().map(AccountRolePO::getRoleId).toList());
        }
        return account;
    }

    public static AccountPO toPO(Account entity) {
        if (entity == null)
            return null;
        AccountPO po = new AccountPO();
        po.setId(entity.getId());
        po.setUsername(entity.getUsername());
        po.setPassword(entity.getPassword());
        po.setEmail(entity.getEmail()); // 设置 email 字段
        return po;
    }
}
