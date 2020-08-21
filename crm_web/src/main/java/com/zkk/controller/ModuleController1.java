package com.zkk.controller;


import com.zkk.entity.Module;
import com.zkk.service.ModuleService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 **/
@RestController
@RequestMapping("/module1")
public class ModuleController1 {
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/getList")
    public List<Module> getList() {
        return moduleService.getList();
    }

    @GetMapping("/showPage")
    public Pagination showPage(Integer pageSize, Integer pageNo) {
        Pagination page = moduleService.getPage(pageSize, pageNo);
        return page;
    }

    /**
     * 删除一个部门
     **/
    @PostMapping("/delete")
    public String delete(@RequestBody Integer[] ids) {

        if (ids.length == 0) {
            return "error";
        } else {
            moduleService.delete(ids);
            return "ok";
        }
    }

    @PostMapping("/add")
    public String add(@RequestBody Module module) {
        moduleService.add(module);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Module module) {
        moduleService.update(module);
        return "ok";
    }
}
