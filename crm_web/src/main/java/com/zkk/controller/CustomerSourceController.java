package com.zkk.controller;

import com.zkk.entity.CustomerSource;
import com.zkk.service.CustomerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/21 15:04
 */
@RestController
@RequestMapping("/customerSource")
public class CustomerSourceController {

    @Autowired
    private CustomerSourceService customerSourceService;

    @GetMapping("/customerSourceList")
    public List<CustomerSource> getList(){
        return customerSourceService.getList();
    }
}
