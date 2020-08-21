package com.zkk.controller;

import com.zkk.entity.Module;
import com.zkk.entity.vo.ZtreeVo;
import com.zkk.service.ModuleService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 23:09
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/getAll")
    public List<Module> getList() {
        return moduleService.getList();
    }

    @GetMapping("/getModulesOneList")
    public List<Module> getModulesOneList(){
        return moduleService.getModulesOneList();
    }

    @GetMapping("/getModulesTwoList")
    public List<Module> getModulesTwoList(Integer parentId){
        return moduleService.getModulesTwoList(parentId);
    }

    @GetMapping("/page/{current}/{pageSize}")
    public Pagination getPage(@PathVariable Integer current,@PathVariable Integer pageSize){
        return moduleService.getPage(pageSize,current);
    }
    @GetMapping("/ztreeNodes")
    public List<ZtreeVo> getZtreeNode(){
        //调用业务方法，获取所有模块对象
        List<Module> modules = moduleService.getList();
        // 遍历模块集合，组装vo对象集合
        List<ZtreeVo> ztreeList = new ArrayList<>();
        for (Module module : modules) {
            ZtreeVo ztreeVo = new ZtreeVo();
            ztreeVo.setId(module.getId());
            ztreeVo.setpId(module.getParentId());
            ztreeVo.setName(module.getName());
            ztreeVo.setChecked(false);//默认节点都不选中
            ztreeVo.setOpen(true);//默认节点展开

            ztreeList.add(ztreeVo);
        }
        return ztreeList;
    }
    @GetMapping("/changeState/{id}/{state}")
    public String changeState(@PathVariable Integer id,@PathVariable Short state){
        moduleService.changeState(id,state);
        return "ok";
    }
    @GetMapping("/parentList")
    public List<Module> getParentList(){
        return moduleService.getModulesOneList();
    }
}
