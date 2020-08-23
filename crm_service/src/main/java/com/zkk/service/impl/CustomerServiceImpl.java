package com.zkk.service.impl;

import com.zkk.dao.CustomerDao;
import com.zkk.entity.Customer;
import com.zkk.service.CustomerService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/18 0:48
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<String> getRepeatPhones(List<String> customerPhones) {
        return customerDao.getRepeatPhones(customerPhones);
    }

    @Override
    public void addExcelCustomer(Customer customer) {
        customerDao.insert(customer);
    }

    @Override
    public Pagination getPage(Integer empId, Integer pageSize, Integer current) {
        return customerDao.getPage("from Customer where add_person_id = ?1 and department_id is null", current, pageSize, empId);
    }

    @Override
    public void updateEmp(Integer[] ids, Integer companyId, Integer deptId, Integer empId) {
        for (Integer id : ids) {
            customerDao.update("update Customer set company_id = ?1,department_id = ?2,employee_id = ?3,state = 2 where id = ?4", companyId, deptId, empId, id);
        }
    }

    @Override
    public void updateDept(Integer[] ids, Integer companyId, Integer deptId) {
        for (Integer id : ids) {
            customerDao.update("update Customer set company_id = ?1,department_id = ?2,state = 1 where id = ?3", companyId, deptId, id);
        }
    }

    @Override
    public void toEmpAll(Integer companyId, Integer deptId, Integer empId) {
        customerDao.update("update Customer set company_id = ?1,department_id = ?2,employee_id = ?3,state = 2",companyId,deptId,empId);
    }

    @Override
    public void toDeptAll(Integer companyId, Integer deptId) {
        customerDao.update("update Customer set company_id = ?1,department_id = ?2,state = 1",companyId,deptId);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerDao.getList("from Customer where datediff(now(),fllow_date)  > 30 and state not in(4,5,6,7,8,9,10) and (visibility = 1 or visibility is null) and publicView is null");
    }

    @Override
    public void updateToPublicView(Integer id) {
        customerDao.update("update Customer set public_view = 1,state = 9,release_time = now(),release_type = 1,release_id = 0 where id = ?1",id);
    }

    @Override
    public List<Object[]> getMonthStatistics(Integer companyId, String date) {
        return customerDao.selectBySql("select add_person_name,count(id) from customer  where DATE_FORMAT(create_date, '%Y-%m-%d') >= ? AND DATE_FORMAT(create_date, '%Y-%m-%d') <= DATE_ADD(?,INTERVAL 1 MONTH) and company_id = ? GROUP BY add_person_name;",date,date,companyId);
    }

    @Override
    public List<Object[]> getWeekDatas(Integer companyId) {
        return customerDao.selectBySql("SELECT add_person_name,COUNT(id) from customer where WEEK(create_date)=WEEK(now()) and company_id=?1 GROUP BY add_person_name",companyId);
    }

    @Override
    public Pagination getPageNew(Integer id, Integer pageSize, Integer current) {
        return customerDao.getPage("from Customer where emp.id = ?1 and state = 0",current,pageSize,id);
    }

    @Override
    public void update(Customer customer, Integer state) {
        customer.setState(state);
        customerDao.update(customer);
    }

    @Override
    public List<Customer> getAllCustomerByEmpId(Integer empId) {
        return customerDao.getList("from Customer where emp.id = ?1",empId);
    }

    @Override
    public Integer getImportNum(Integer id) {
        return customerDao.getCount("from Customer where customerRoster.id = ?1",id);
    }

    @Override
    public Integer getNotAllocatedNum(Integer id) {
        return customerDao.getCount("from Customer where dept.id is null and customerRoster.id = ?1",id);
    }

}
