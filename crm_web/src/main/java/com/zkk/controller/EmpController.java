package com.zkk.controller;

import com.zkk.entity.Emp;
import com.zkk.entity.vo.EmpRoleVo;
import com.zkk.service.EmpService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 14:40
 */
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("/getPage")
    public Pagination getPage(Integer pageSize, Integer current) {
        return empService.getPage(pageSize, current);
    }

    @PostMapping("/add")
    public String addEmp(@RequestBody Emp emp) {
        empService.addEmp(emp);
        return "ok";
    }

    @PostMapping("/update")
    public String updateEmp(@RequestBody Emp emp) {
        empService.updateEmp(emp);
        return "ok";
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestBody EmpRoleVo empRoleVo) {
        empService.updateRole(empRoleVo);
        return "ok";
    }

    @PostMapping("/iceEmp")
    public String iceEmp(@RequestBody Integer[] ids) {
        empService.iceEmp(ids);
        return "ok";
    }

    @PostMapping("/updateIsIce")
    public String updateIsIce(@RequestBody Integer[] ids) {
        empService.updateIsIce(ids);
        return "ok";
    }

    @GetMapping("/getEmpByDeptId/{id}")
    public List<Emp> getEmpByDeptId(@PathVariable Integer id) {
        return empService.getEmpByDeptId(id);
    }
}
