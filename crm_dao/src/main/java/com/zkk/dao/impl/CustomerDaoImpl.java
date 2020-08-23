package com.zkk.dao.impl;

import com.zkk.dao.CustomerDao;
import com.zkk.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/18 0:49
 */
@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements CustomerDao {
    @Override
    public List<String> getRepeatPhones(List<String> customerPhones) {
        Query query = getEntityManager().createQuery("select phone from Customer where phone in (" + customerPhones.toString().substring(1, customerPhones.toString().length() - 1) + ")");
        List<String> list = query.getResultList();
        return list;
    }

    @Override
    public List<Object[]> selectBySql(String sql, Object... params) {
        Query nativeQuery = getEntityManager().createNativeQuery(sql);
        // 设置查询条件
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                nativeQuery.setParameter((i + 1), params[i]);
            }
        }
        List list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public Integer getStateByPhone(String phone) {
        Query query = getEntityManager().createQuery("select state from Customer where phone=?1");
        query.setParameter(1,phone);
        Integer state = (Integer) query.getSingleResult();
        return state;
    }



}
