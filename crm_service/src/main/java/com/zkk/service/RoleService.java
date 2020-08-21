package com.zkk.service;

import com.zkk.entity.Role;
import com.zkk.utils.Pagination;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/11 18:17
 */
public interface RoleService {
    /**
     * 分页数据
     * @param pageSize 每页大小
     * @param current 页码
     * @return 分页数据
     */
    Pagination getPage(Integer pageSize, Integer current);

    /**
     * 获取当前公司所有角色
     * @param companyId
     * @return
     */
    List<Role> getAllRolesById(Integer companyId);

    /**
     * 新增角色
     * @param role
     * @param modules
     */
    void add(Role role);

    /**
     * 删除角色
     * @param ids
     */
    void delete( Integer[] ids);

    /**
     * 修改
     * @param role
     */
    void update(Role role);

    List<Role> getAll();

    List<Role> getEmpRole(Integer companyId);
}
