package com.zkk.service.impl;

import com.zkk.dao.CompanyDao;
import com.zkk.entity.Company;
import com.zkk.service.CompanyService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/10 0:51
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Override
    public List<Company> getCompanyList() {
        return companyDao.getList("from Company where state != ?1",2);
    }

    @Override
    public Pagination getPage(Integer pageSize, Integer current) {
        System.out.println(pageSize);
        System.out.println("c"+current);
        return companyDao.getPage("from Company",current,pageSize);
    }

    @Override
    public void add(Company company) {
        companyDao.insert(company);
    }

    @Override
    public void delete(Integer[] ids, String updateBy) {
        for (Integer id : ids) {
            companyDao.update("update Company set state = 1,update_by = ?2 where id = ?1",id,updateBy);
        }

    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }
}
