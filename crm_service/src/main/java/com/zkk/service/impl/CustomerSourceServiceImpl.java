package com.zkk.service.impl;

import com.zkk.dao.CustomerSourceDao;
import com.zkk.entity.CustomerSource;
import com.zkk.service.CustomerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/21 15:04
 */
@Service
public class CustomerSourceServiceImpl implements CustomerSourceService {
    @Autowired
    private CustomerSourceDao customerSourceDao;

    @Override
    public List<CustomerSource> getList() {
        return customerSourceDao.getList("from CustomerSource");
    }
}
