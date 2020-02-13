package com.leyiju.mapper;

import com.leyiju.domain.GroupRole;

import java.util.List;

public interface GroupRoleMapper {

    List<GroupRole> getGroupWithRoles(Long roleId);
}
