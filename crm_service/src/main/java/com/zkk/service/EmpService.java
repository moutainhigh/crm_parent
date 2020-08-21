package com.zkk.service;

import com.zkk.entity.Emp;
import com.zkk.entity.vo.EmpRoleVo;
import com.zkk.utils.Pagination;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 14:41
 */
public interface EmpService {
    /**
     * 分页查询
     * @param pageSize 每页大小
     * @param current 页码
     * @return
     */
    Pagination getPage(Integer pageSize, Integer current);

    /**
     * 增加
     * @param emp
     */
    void addEmp(Emp emp);

    /**
     * 修改
     * @param emp
     */
    void updateEmp(Emp emp);

    /**
     * 分配角色
     * @param empRoleVo 视图对象
     */
    void updateRole(EmpRoleVo empRoleVo);

    /**
     * 冻结员工
     * @param ids
     */
    void iceEmp(Integer[] ids);

    /**
     * 解除冻结
     * @param ids
     */
    void updateIsIce(Integer[] ids);


    Emp getEmpByUsername(String username);

    List<Emp> getEmpByDeptId(Integer id);
}
