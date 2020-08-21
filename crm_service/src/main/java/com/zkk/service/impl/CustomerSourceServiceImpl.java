package com.zkk.service.impl;

import com.zkk.dao.CustomerSourceDao;
import com.zkk.service.CustomerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/21 15:04
 */
@Service
public class CustomerSourceServiceImpl implements CustomerSourceService {
    @Autowired
    private CustomerSourceDao customerSourceDao;
}
