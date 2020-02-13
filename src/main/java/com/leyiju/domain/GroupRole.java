package com.leyiju.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 权限组角色关联类
 */
public class GroupRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="ID",name="id", example = "1")
    private Long id;

    @ApiModelProperty(value="groupId",name="groupId", example = "1")
    private Long groupId;

    @ApiModelProperty(value="roleId",name="roleId", example = "1")
    private Long roleId;

    private Group group;

    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
