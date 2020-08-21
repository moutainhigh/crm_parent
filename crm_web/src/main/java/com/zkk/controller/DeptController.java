package com.zkk.controller;

import com.zkk.entity.Dept;
import com.zkk.service.DeptService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/9 22:37
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取分页数据
     */
    @GetMapping("/page")
    public Pagination page(Integer current, Integer pageSize) {
        Pagination pagination = deptService.getPage(current, pageSize);
        return pagination;
    }

    /**
     * 新增方法
     */
    @PostMapping("/add")
    public String addDept(@RequestBody Dept dept) {
        //设置创建者和时间
        dept.setCreateTime(new Date());
        deptService.addDept(dept);
        return "ok";
    }

    /**
     * 删除方法
     */
    @PostMapping("/delete")
    public String deleteDept(@RequestBody Integer[] ids) {
        deptService.deleteDeptById(ids);
        return "ok";
    }

    /**
     * 修改方法
     */
    @PostMapping("/update")
    public String update(@RequestBody Dept dept) {
        //判断部门名称不能为空
        if ("".equals(dept.getName()) || dept.getName() == null) {
            return "false";
        }
        //设置修改时间
        dept.setUpdateTime(new Date());
        //调用业务方法
        deptService.update(dept);
        return "ok";
    }

    /**
     * 部门集合
     */
    @GetMapping("/list")
    public List<Dept> getList() {
        List<Dept> deptList = deptService.getAll();
        return deptList;
    }

    @GetMapping("/getDeptByCompanyId/{id}")
    public List<Dept> getList(@PathVariable Integer id) {
        List<Dept> deptList = deptService.getDeptByCompanyId(id);
        return deptList;
    }
}
