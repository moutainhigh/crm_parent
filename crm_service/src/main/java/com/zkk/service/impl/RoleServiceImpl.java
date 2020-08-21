package com.zkk.service.impl;


import com.zkk.dao.RoleDao;
import com.zkk.entity.Role;
import com.zkk.service.RoleService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleDao.getList("from Role");
        return roles;
    }

    @Override
    public List<Role> getEmpRole(Integer companyId) {
        return roleDao.getList("from Role where company.id = ?1",companyId);
    }

    @Override
    public Pagination getPage(Integer pageSize, Integer current) {
        Pagination pagination = roleDao.getPage("from Role ", current, pageSize);
        return pagination;
    }

    @Override
    public List<Role> getAllRolesById(Integer companyId) {
        return roleDao.getList("from Role where company.id = ?1",companyId);
    }

    @Override
    public void add(Role role) {
        roleDao.insert(role);
    }

    @Override
    public void delete(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            roleDao.delete(ids[i]);
        }
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }
}
