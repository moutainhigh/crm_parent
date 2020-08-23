package com.zkk.dao.impl;

import com.zkk.dao.CustomerRosterDao;
import com.zkk.entity.CustomerRoster;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/19 16:33
 */
@Repository
public class CustomerRosterDaoImpl extends BaseDaoImpl<CustomerRoster, Integer> implements CustomerRosterDao {
    @Override
    public CustomerRoster add(CustomerRoster customerRoster) {
        return getEntityManager().merge(customerRoster);
    }

    @Override
    public String getName(Integer id) {
        Query query = getEntityManager().createQuery("select name from CustomerRoster where id = ?1");
        query.setParameter(1,id);
        String name = (String) query.getSingleResult();
        return name;
    }
}
