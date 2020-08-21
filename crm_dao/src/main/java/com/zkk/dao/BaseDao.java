package com.zkk.dao;

import com.zkk.utils.Pagination;

import java.io.Serializable;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/10 15:04
 * 泛型接口,简化Dao开发
 */
public interface BaseDao<T, ID extends Serializable> {
    /**
     * 获取总的数据条数
     * @param hql 必填的hql语句
     * @param params 可选的参数
     * @return 总的数据条数
     */
    Integer getCount(String hql,Object... params);

    /**
     * 获取分页数据
     * @param hql 必填的hql语句
     * @param pageNo 必填的分页页码
     * @param pageSize 必填的每页条数
     * @param params 可选的参数
     * @return 分页数据
     */
    List<T> getPageList(String hql,Integer pageNo,Integer pageSize,Object... params);

    /**
     * 获取分页对象
     * @param hql 必填的hql语句
     * @param pageNo 必填的分页页码
     * @param pageSize 必填的每页条数
     * @param params 可选的参数
     * @return 分页对象
     */
    Pagination getPage(String hql,Integer pageNo,Integer pageSize,Object... params);

    /**
     * 获取结果集对象
     * @param hql 必填的hql语句
     * @param params 可选的参数
     * @return
     */
    List<T> getList(String hql,Object... params);

    /**
     * 新增
     * @param t 对象
     */
    void insert(T t);

    /**
     * 删除
     * @param id id
     */
    void delete(ID id);

    /**
     * 更新
     * @param t 对象
     */
    void update(T t);

    /**
     * 根据主键获取单个对象
     * @param id id
     * @return 单个对象
     */
    T get(ID id);

    /**
     * 修改
     * @param hql 必填的hql语句
     * @param params 可选的参数
     */
    void update(String hql,Object... params);
}
