package com.leyiju.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 菜单
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID", name = "id", example = "1")
    private Long id;

    private String menuName;

    private Long parentId;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
