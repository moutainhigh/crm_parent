package com.zkk.controller;

import com.zkk.service.CustomerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/21 15:04
 */
@RestController
public class CustomerSourceController {

    @Autowired
    private CustomerSourceService customerSourceService;
}
