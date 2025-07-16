package com.example.logindemo.service.impl;

import java.util.Set;

public interface RoleService {
    /**
     * 批量授权
     * @param roleId
     * @param permissionIds
     */
    void batchAuthorize(Long roleId, Set<Long> permissionIds);
}
