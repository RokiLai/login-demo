package com.example.logindemo.infra.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.example.logindemo.domain.model.Role;
import com.example.logindemo.infra.po.RolePO;

public class RoleConverter {
    public static RolePO toPO(Role role) {
        if (role == null) {
            return null;
        }
        RolePO rolePO = new RolePO();
        rolePO.setId(role.getId());
        return rolePO;
    }

    public static Role toEntity(RolePO rolePO) {
        if (rolePO == null) {
            return null;
        }
        Role role = new Role();
        role.setId(rolePO.getId());
        role.setCode(rolePO.getCode());
        return role;
    }

    public static List<Role> convertList(List<RolePO> rolePOs) {
        if (CollectionUtils.isEmpty(rolePOs)) {
            return Collections.emptyList();
        }
        return rolePOs.stream().map(RoleConverter::toEntity).collect(Collectors.toList());
    }




}
