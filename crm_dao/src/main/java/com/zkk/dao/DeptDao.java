package com.zkk.dao;

import com.zkk.entity.Dept;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/9 22:41
 */
public interface DeptDao extends BaseDao<Dept,Integer>{
    /**
     * 查询所有的数量总数
     * @return 数量总数
     */
    Integer getCounts();

    /**
     * 返回结果集
     * @param current 当前页码
     * @param pageSize 每页条数
     * @return 数据结果集
     */
    List<Dept> getList(Integer current, Integer pageSize);

    /**
     * 新增部门
     * @param dept 部门
     */
    void insert(Dept dept);

    /**
     * 删除部门
     * @param id id集
     */
    void remove(Integer id);

    /**
     * 修改部门
     * @param dept 部门
     */
    void update(Dept dept);
}
