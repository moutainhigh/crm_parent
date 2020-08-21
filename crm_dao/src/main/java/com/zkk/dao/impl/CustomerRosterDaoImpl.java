package com.zkk.dao.impl;

import com.zkk.dao.CustomerRosterDao;
import com.zkk.entity.CustomerRoster;
import org.springframework.stereotype.Repository;

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
}
