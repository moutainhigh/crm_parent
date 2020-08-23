package com.zkk.entity.vo;

import java.util.Date;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/23 14:20
 * 客户分配vo
 */
public class CustomerAllocationVo {
    //名单名称
    private String name;
    //导入事件
    private String date;
    //导入数量
    private Integer importNum;
    //已分配数量
    private Integer allocatedNum;
    //未分配数量
    private Integer notAllocatedNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getImportNum() {
        return importNum;
    }

    public void setImportNum(Integer importNum) {
        this.importNum = importNum;
    }

    public Integer getAllocatedNum() {
        return allocatedNum;
    }

    public void setAllocatedNum(Integer allocatedNum) {
        this.allocatedNum = allocatedNum;
    }

    public Integer getNotAllocatedNum() {
        return notAllocatedNum;
    }

    public void setNotAllocatedNum(Integer notAllocatedNum) {
        this.notAllocatedNum = notAllocatedNum;
    }
}
