package com.zkk.controller;

import com.zkk.entity.Company;
import com.zkk.service.CompanyService;
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
 * @date 2020/8/10 0:47
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public List<Company> getCompanyList() {
        return companyService.getCompanyList();
    }

    @GetMapping("/showPage")
    public Pagination getPage(Integer pageSize, Integer pageNo) {
        return companyService.getPage(pageSize, pageNo);
    }

    @PostMapping("/add")
    public String addCompany(@RequestBody Company company) {
        companyService.add(company);
        return "ok";
    }

    @PostMapping("/delete/{updateBy}")
    public String delete(@RequestBody Integer[] ids,@PathVariable String updateBy) {
        companyService.delete(ids,updateBy);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Company company){
        companyService.update(company);
        return "ok";
    }
}
