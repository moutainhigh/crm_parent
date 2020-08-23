package com.zkk.dao;

import com.zkk.entity.Customer;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/18 0:49
 */
public interface CustomerDao extends BaseDao<Customer,Integer>{
    List<String> getRepeatPhones(List<String> customerPhones);

    List<Object[]> selectBySql(String sql, Object...params);

    Integer getStateByPhone(String phone);


}
