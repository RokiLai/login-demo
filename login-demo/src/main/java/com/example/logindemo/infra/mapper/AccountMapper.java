package com.example.logindemo.infra.mapper;

import com.example.logindemo.infra.po.AccountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Select("SELECT id, username, password, email FROM account WHERE username = #{username}")
    AccountPO findByUsername(String username);
}
