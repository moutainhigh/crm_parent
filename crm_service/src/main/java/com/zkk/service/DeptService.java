package com.zkk.service;

import com.zkk.entity.Dept;
import com.zkk.utils.Pagination;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/9 22:40
 */
public interface DeptService {
    /**
     * 获取分页数据
     * @param current 当前页码
     * @param pageSize 每页条数
     * @return
     */
    Pagination getPage(Integer current, Integer pageSize);

    /**
     * 新增部门
     * @param dept 部门
     */
    void addDept(Dept dept);

    /**
     * 删除部门
     * @param ids id集
     */
    void deleteDeptById(Integer[] ids);

    /**
     * 修改本名
     * @param dept 部门
     */
    void update(Dept dept);

    /**
     * 部门集合
     * @return
     */
    List<Dept> getAll();

    /**
     * 根据公司查询部门
     * @param companyId 公司id
     * @return
     */
    List<Dept> getDeptByCompanyId(Integer companyId);
}
