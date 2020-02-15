package com.leyiju.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 权限资源对象
 */
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID", name = "id", example = "1")
    private Long id;

    private Long resourceId;

    private Long groupId;

    private Long roleId;

    @ApiModelProperty(value = "资源类型", name = "resourceType", example = "1 菜单 2 内容")
    private String resourceType;

    @ApiModelProperty(value = "创建时间", name = "createTime", example = "1559719525000")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "1559719525000")
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
