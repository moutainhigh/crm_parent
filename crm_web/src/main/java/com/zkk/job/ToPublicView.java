package com.zkk.job;

import com.zkk.entity.Customer;
import com.zkk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/19 23:36
 */
public class ToPublicView {
    @Autowired
    private CustomerService customerService;

    //任务调度
    public void toPublicView() {
        List<Customer> customers = customerService.getAllCustomer();
        Set<Integer> needUpdate = new HashSet<>();
        for (Customer customer : customers) {
            Integer id = customer.getId();
            needUpdate.add(id);
        }
        //更新
        for (Integer id : needUpdate) {
            customerService.updateToPublicView(id);
        }
    }
}
