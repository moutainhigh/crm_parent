package com.zkk.service;

import com.zkk.entity.Module;
import com.zkk.utils.Pagination;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 23:10
 */
public interface ModuleService {
    /**
     * 模块集合
     * @return 模块集合
     */
    List<Module> getList();

    List<Module> getModulesOneList();

    List<Module> getModulesTwoList(Integer parentId);

    Pagination getPage(Integer pageSize, Integer pageNo);

    void delete(Integer[] ids);

    void add(Module module);

    void update(Module module);

    void changeState(Integer id, Short state);
}
