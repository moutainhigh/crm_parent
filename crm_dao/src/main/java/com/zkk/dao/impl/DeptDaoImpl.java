package com.zkk.dao.impl;

import com.zkk.dao.DeptDao;
import com.zkk.entity.Dept;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/9 22:41
 */
@Repository
@SuppressWarnings("all")
public class DeptDaoImpl extends BaseDaoImpl<Dept, Integer> implements DeptDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer getCounts() {
        Query query = entityManager.createQuery("select count(*) from  Dept");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Dept> getList(Integer current, Integer pageSize) {
        Query query = entityManager.createQuery("from Dept");
        query.setFirstResult((current - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Dept> depts = query.getResultList();
        return depts;
    }

    @Override
    public void insert(Dept dept) {
        entityManager.persist(dept);
    }

    @Override
    public void remove(Integer id) {
        Dept dept = entityManager.find(Dept.class, id);
        entityManager.remove(dept);
    }

    @Override
    public void update(Dept dept) {
        entityManager.merge(dept);
    }
}
