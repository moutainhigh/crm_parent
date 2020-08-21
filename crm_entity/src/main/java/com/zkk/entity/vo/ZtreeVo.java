package com.zkk.entity.vo;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/14 9:13
 */
public class ZtreeVo {
    //{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true}
    private Integer id;//节点id
    private Integer pId;//父节点id
    private String name;//节点名称
    private Boolean checked = false;//默认不选中
    private Boolean open = true;//默认展开二级菜单

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}

