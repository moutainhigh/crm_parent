package com.zkk.service.impl;

import com.zkk.dao.DeptDao;
import com.zkk.entity.Dept;
import com.zkk.service.DeptService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/9 22:40
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public Pagination getPage(Integer current, Integer pageSize) {
        // 主装分页对象
        // 获取总数据数量
        Integer counts = deptDao.getCounts();
        // 获取当前页码的数据集
        List<Dept> deptList = deptDao.getList(current, pageSize);
        //返回分页对象
        Pagination pagination = new Pagination(current, pageSize, counts, deptList);
        return pagination;
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.insert(dept);
    }

    @Override
    public void deleteDeptById(Integer[] ids) {
        for (Integer id : ids) {
            deptDao.remove(id);
        }
    }

    @Override
    public void update(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public List<Dept> getAll() {
        return deptDao.getList("from Dept");
    }

    @Override
    public List<Dept> getDeptByCompanyId(Integer companyId) {
        return deptDao.getList("from Dept where company_id=?1", companyId);
    }
}
