package com.leyiju.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户角色关联类
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="ID",name="id", example = "1")
    private Long id;

    @ApiModelProperty(value="userId",name="userid", example = "1")
    private Long userId;

    @ApiModelProperty(value="roleId",name="roleId", example = "1")
    private Long roleId;

    private User users;

    private Role roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
