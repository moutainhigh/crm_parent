package com.zkk.entity;

import javax.persistence.*;

/**
 *
 **/
@Entity
@Table(name = "module")
public class Module extends BaseEntity{
    @Id
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String icon;
    @Column
    private String url = "/";
    @Column(name = "parent_id")
    private Integer parentId;
    @Column
    private Short state = 0;
    @Column
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
