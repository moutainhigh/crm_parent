package com.zkk.service.impl;

import com.zkk.dao.CustomerRosterDao;
import com.zkk.entity.CustomerRoster;
import com.zkk.service.CustomerRosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/19 16:32
 */
@Service
public class CustomerRosterServiceImpl implements CustomerRosterService {

    @Autowired
    private CustomerRosterDao customerRosterDao;

    @Override
    public CustomerRoster add(CustomerRoster customerRoster) {
        return customerRosterDao.add(customerRoster);
    }

    @Override
    public String getName(Integer id) {
        return customerRosterDao.getName(id);
    }

    @Override
    public CustomerRoster get(Integer id) {
        return customerRosterDao.get(id);
    }
}
