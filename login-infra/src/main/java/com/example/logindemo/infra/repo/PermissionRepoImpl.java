package com.example.logindemo.infra.repo;

import com.example.logindemo.domain.repo.PermissionRepo;
import com.example.logindemo.infra.mapper.PermissionMapper;
import com.example.logindemo.infra.po.PermissionPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class PermissionRepoImpl implements PermissionRepo {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> selectCodeByIds(List<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {

            return List.of();
        }
        return permissionMapper.selectByIds(permissionIds).stream().map(PermissionPO::getCode).toList();
    }
}
