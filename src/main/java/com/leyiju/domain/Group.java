package com.leyiju.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分组
 */
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "权限组名称", name = "groupName", example = "管理员组")
    private String groupName;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
