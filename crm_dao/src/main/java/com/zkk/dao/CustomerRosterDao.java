package com.zkk.dao;

import com.zkk.entity.CustomerRoster;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/19 16:32
 */
public interface CustomerRosterDao extends BaseDao<CustomerRoster,Integer>{
    CustomerRoster add(CustomerRoster customerRoster);

    String getName(Integer id);
}
