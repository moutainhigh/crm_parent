package com.zkk.controller;


import com.zkk.entity.Role;
import com.zkk.service.RoleService;
import com.zkk.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource(name = "roleService")
    private RoleService roleService;

    @RequestMapping("/list")
    public List<Role> getAll(){
        List<Role> roles = roleService.getAll();
        return roles;
    }

    @RequestMapping("/getPage")
    public Pagination getPage(Integer pageSize, Integer current){
        Pagination pagination=roleService.getPage(pageSize,current);
        return pagination;
    }

    @PostMapping("/add")
    public String add(@RequestBody Role role){
        System.out.println(role);
        roleService.add(role);
        return "ok";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Integer[] ids){
        roleService.delete(ids);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Role role){
        roleService.update(role);
        return "ok";
    }
    @RequestMapping("/empRole/{companyId}")
    public List<Role> getEmpRole(@PathVariable Integer companyId){
        return roleService.getEmpRole(companyId);
    }

}
