package com.example.logindemo.infra.repo;

import java.util.Collections;
import java.util.List;

import com.example.logindemo.domain.model.Permission;
import com.example.logindemo.infra.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.example.logindemo.domain.model.Role;
import com.example.logindemo.domain.repo.RoleRepo;
import com.example.logindemo.infra.converter.RoleConverter;
import com.example.logindemo.infra.mapper.RoleMapper;
import com.example.logindemo.infra.po.RolePO;

@Repository
public class RoleRepoImpl implements RoleRepo {

    private final RoleMapper roleMapper;

    private final RolePermissionMapper rolePermissionMapper;

    public RoleRepoImpl(RoleMapper roleMapper, RolePermissionMapper rolePermissionMapper) {
        this.roleMapper = roleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public List<Role> selectAll() {
        List<RolePO> rolePOs = roleMapper.selectAll();
        if (CollectionUtils.isEmpty(rolePOs)) {
            return Collections.emptyList();
        }
        return RoleConverter.convertList(rolePOs);
    }

    @Override
    public List<Role> selectByIds(List<Long> roleIds) {
        List<RolePO> rolePOs = roleMapper.selectByIds(roleIds);
        if (CollectionUtils.isEmpty(rolePOs)) {
            return Collections.emptyList();
        }
        return RoleConverter.convertList(rolePOs);
    }

    @Override
    public Role selectById(Long roleId) {
        RolePO rolePO = roleMapper.selectById(roleId);
        if (rolePO == null) {
            return null;
        }
        return RoleConverter.toEntity(rolePO);
    }

    @Override
    public List<String> selectCodeByIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return List.of();
        }
        return roleMapper.selectByIds(roleIds).stream().map(RolePO::getCode).toList();
    }

    @Override
    public void updateRolePermission(Role role) {
        rolePermissionMapper.deleteByRoleId(role.getId());
        rolePermissionMapper.insertRolePermissions(role.getId(), role.getPermissionIds());
    }
}
