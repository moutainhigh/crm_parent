package com.zkk.service;

import com.zkk.entity.Company;
import com.zkk.utils.Pagination;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/10 0:50
 */
public interface CompanyService {
    /**
     * 获取公司集合
     *
     * @return 公司集合
     */
    List<Company> getCompanyList();

    /**
     * 分页数据显示
     *
     * @param pageSize 每页大小
     * @param current  页码
     * @return 分页数据
     */
    Pagination getPage(Integer pageSize, Integer current);

    /**
     * 增加公司
     *
     * @param company 公司
     */
    void add(Company company);

    /**
     * 删除公司
     * @param ids id数组
     * @param updateBy
     */
    void delete(Integer[] ids, String updateBy);

    /**
     * 更新公司
     * @param company 公司对象
     */
    void update(Company company);
}
