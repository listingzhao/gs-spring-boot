package com.leyiju.mapper;

import com.leyiju.domain.UserRole;

import java.util.List;

public interface UserRoleMapper {

    public List<UserRole> selectUserWithRoles(Long userId);
}
