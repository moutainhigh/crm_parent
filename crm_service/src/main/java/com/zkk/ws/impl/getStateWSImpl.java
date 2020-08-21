package com.zkk.ws.impl;

import com.zkk.dao.CustomerDao;
import com.zkk.entity.Customer;
import com.zkk.ws.getStateWS;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/20 18:13
 */
public class getStateWSImpl implements getStateWS {
    @Autowired
    private CustomerDao customerDao;
    @Override
    public Integer getStateByPhone(String phone) {
        return customerDao.getStateByPhone(phone);
    }
}
