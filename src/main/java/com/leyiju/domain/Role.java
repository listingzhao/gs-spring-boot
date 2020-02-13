package com.leyiju.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 角色
 */
@ApiModel(value="Role",description="角色对象")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="ID",name="id", example = "1")
    private Long id;

    @ApiModelProperty(value="roleName",name="roleName", example = "管理员")
    private String roleName;

    @ApiModelProperty(value="role",name="role", example = "ROLE_ADMIN")
    private String role;

    @ApiModelProperty(value="创建时间",name="createTime", example = "1559719525000")
    private Long createTime;
    @ApiModelProperty(value="更新时间",name="updateTime", example = "1559719525000")
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
