package com.zkk.entity.vo;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 21:26
 */
public class EmpRoleVo {
    private Integer empId;
    private String empName;
    private Integer[] roleIds;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }
}
