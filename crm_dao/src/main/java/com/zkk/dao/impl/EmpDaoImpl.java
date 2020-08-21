package com.zkk.dao.impl;

import com.zkk.dao.EmpDao;
import com.zkk.entity.Emp;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 14:41
 */
@Repository
public class EmpDaoImpl extends BaseDaoImpl<Emp, Integer> implements EmpDao {


    @Override
    public Emp getEmpByUsername(String username) {
        Query query = getEntityManager().createQuery("from Emp where account = ?1");
        query.setParameter(1,username);
        Emp emp = (Emp) query.getSingleResult();
        return emp;
    }
}
