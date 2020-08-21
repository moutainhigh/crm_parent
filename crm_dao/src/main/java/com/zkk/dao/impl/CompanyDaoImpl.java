package com.zkk.dao.impl;

import com.zkk.dao.CompanyDao;
import com.zkk.entity.Company;
import org.springframework.stereotype.Repository;


/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/10 0:52
 */
@Repository
public class CompanyDaoImpl extends BaseDaoImpl<Company,Integer> implements CompanyDao {

    public CompanyDaoImpl(){
        super();
    }
}
