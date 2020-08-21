package com.zkk.service.impl;

import com.zkk.dao.ModuleDao;
import com.zkk.entity.Module;
import com.zkk.service.ModuleService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 **/
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;

    @Override
    public List<Module> getList() {
        return moduleDao.getList("from Module");
    }

    @Override
    public List<Module> getModulesOneList() {
        return moduleDao.getList("from Module where parent_id = null");
    }

    @Override
    public List<Module> getModulesTwoList(Integer parentId) {
        return moduleDao.getList("from Module where parent_id = ?1",parentId);
    }

    @Override
    public Pagination getPage(Integer pageSize, Integer pageNo) {
        return moduleDao.getPage("from Module", pageNo, pageSize);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            moduleDao.delete(id);
        }
    }

    @Override
    public void add(Module module) {
        moduleDao.insert(module);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public void changeState(Integer id, Short state) {
        moduleDao.update("update Module set state = ?1 where id = ?2",state,id);
    }
}
