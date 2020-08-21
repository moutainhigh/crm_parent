package com.zkk.dao;

import com.zkk.entity.Emp;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 14:41
 */
public interface EmpDao extends BaseDao<Emp,Integer>{


    Emp getEmpByUsername(String username);
}
