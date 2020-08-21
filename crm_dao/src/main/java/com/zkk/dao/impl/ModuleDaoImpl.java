package com.zkk.dao.impl;


import com.zkk.dao.ModuleDao;
import com.zkk.entity.Module;
import org.springframework.stereotype.Repository;

/**
 *
 **/
@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl<Module,Integer> implements ModuleDao {
}
