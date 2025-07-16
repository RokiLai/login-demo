package com.example.logindemo.infra.mapper;

import com.example.logindemo.infra.po.PermissionPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {

    PermissionPO selectById(@Param("permissionId") Long permissionId);

    List<PermissionPO> selectByIds(@Param("permissionIds") List<Long> permissionIds);

}
