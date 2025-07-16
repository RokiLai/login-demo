package com.example.logindemo.infra.repo;

import com.example.logindemo.domain.repo.RolePermissionRepo;
import com.example.logindemo.infra.mapper.RolePermissionMapper;
import com.example.logindemo.infra.po.RolePermissionPO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class RolePermissionRepoImpl implements RolePermissionRepo {

    private final RolePermissionMapper rolePermissionMapper;

    public RolePermissionRepoImpl(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }


    @Override
    public List<Long> findPermissionIdsByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return List.of();
        }
        return rolePermissionMapper.findByRoleIds(roleIds).stream().map(RolePermissionPO::getPermissionId).toList();
    }

}
