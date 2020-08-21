package com.zkk.dao.impl;

import com.zkk.dao.BaseDao;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/10 15:15
 */
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public BaseDaoImpl() {
        //获取当前类对象的父类class
        Type superclass = this.getClass().getGenericSuperclass();
        //获取参数数据类型数组
        Type[] typeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
        //将类参数类型转化为字节码
        entityClass = (Class<T>) typeArguments[0];
    }

    /**
     * 方便子类获取EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Integer getCount(String hql, Object... params) {
        hql = "select count(*)" + hql;
        Query query = entityManager.createQuery(hql);
        //判断参数列表是不是有值
        if (params != null && params.length > 0) {
            //遍历参数列表
            for (int i = 0; i < params.length; i++) {
                //给占位符赋值,让他从1开始
                query.setParameter(i + 1, params[i]);
            }
        }
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<T> getPageList(String hql, Integer pageNo, Integer pageSize, Object... params) {
        Query query = entityManager.createQuery(hql);
        //判断参数列表是不是有值
        if (params != null && params.length > 0) {
            //遍历参数列表
            for (int i = 0; i < params.length; i++) {
                //给占位符赋值,让他从1开始
                query.setParameter(i + 1, params[i]);
            }
        }
        //设置分页参数
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<T> list = query.getResultList();
        return list;
    }

    @Override
    public Pagination getPage(String hql, Integer pageNo, Integer pageSize, Object... params) {
        Integer count = getCount(hql, params);
        //组装分页对象
        Pagination pagination = new Pagination(pageNo, pageSize, count);
        List<T> pageList = getPageList(hql, pagination.getPageNo(), pageSize, params);
        pagination.setList(pageList);
        return pagination;
    }

    @Override
    public List<T> getList(String hql, Object... params) {
        Query query = entityManager.createQuery(hql);
        //判断参数列表是不是有值
        if (params != null && params.length > 0) {
            //遍历参数列表
            for (int i = 0; i < params.length; i++) {
                //给占位符赋值,让他从1开始
                query.setParameter(i + 1, params[i]);
            }
        }
        List<T> list = query.getResultList();
        return list;
    }

    @Override
    public void insert(T t) {
        entityManager.persist(t);
    }

    @Override
    public void delete(ID id) {
        T t = get(id);
        entityManager.remove(t);
    }

    @Override
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    public T get(ID id) {
        T t = entityManager.find(entityClass, id);
        return t;
    }

    @Override
    public void update(String hql, Object... params) {
        Query query = entityManager.createQuery(hql);
        //判断参数列表是不是有值
        if (params != null && params.length > 0) {
            //遍历参数列表
            for (int i = 0; i < params.length; i++) {
                //给占位符赋值,让他从1开始
                query.setParameter(i + 1, params[i]);
            }
        }
        query.executeUpdate();
    }
}
