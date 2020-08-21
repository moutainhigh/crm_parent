package com.zkk.service;

import com.zkk.entity.Customer;
import com.zkk.utils.Pagination;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/18 0:48
 */
public interface CustomerService {
    List<String> getRepeatPhones(List<String> customerPhones);

    void addExcelCustomer(Customer customer);

    Pagination getPage(Integer empId, Integer pageSize, Integer current);

    void updateEmp(Integer[] ids, Integer companyId, Integer deptId, Integer empId);

    void updateDept(Integer[] ids, Integer companyId, Integer deptId);

    void toEmpAll(Integer companyId, Integer deptId, Integer empId);

    void toDeptAll(Integer companyId, Integer deptId);

    List<Customer> getAllCustomer();

    void updateToPublicView(Integer id);

    List<Object[]> getMonthStatistics(Integer companyId, String format);

    List<Object[]> getWeekDatas(Integer companyId);

    Pagination getPageNew(Integer id, Integer pageSize, Integer current);

    void update(Customer customer, Integer state);
}
