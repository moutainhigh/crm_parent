package com.zkk.controller;

import com.zkk.entity.Emp;
import com.zkk.entity.vo.PieStatVo;
import com.zkk.service.CustomerService;
import com.zkk.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/20 0:15
 */
@RestController
@RequestMapping("/stat")
public class StatisticsController {
    @Autowired
    private EmpService empService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/monthData/{account}/{monthTime}")
    public List<PieStatVo> monthData(@PathVariable String account, @PathVariable Date monthTime) {
        Emp emp = empService.getEmpByUsername(account);
        Integer companyId = emp.getCompany().getId();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01");
        String format = simpleDateFormat.format(monthTime);
        System.out.println(format);
        List<Object[]> monthStatistics = customerService.getMonthStatistics(companyId, format);
        List<PieStatVo> pieStatVos = new ArrayList<>();
        for (Object[] monthStatistic : monthStatistics) {
            PieStatVo pieStatVo = new PieStatVo();
            pieStatVo.setName((String) monthStatistic[0]);
            pieStatVo.setValue((BigInteger) monthStatistic[1]);
            pieStatVos.add(pieStatVo);
        }
        System.out.println(pieStatVos);
        return pieStatVos;
    }

    @GetMapping("/market/week/{username}")
    public Map<String,Object> weekData(@PathVariable String username){
        //根据账户查员工
        Emp emp = empService.getEmpByUsername(username);
        //获取员工公司id
        Integer companyId = emp.getCompany().getId();
        //创建返回的数据容器
        Map<String,Object> dataMap = new HashMap<String,Object>();
        //根据公司获取周数据
        List<Object[]> weekDatas = customerService.getWeekDatas(companyId);
        //设置第一个数据
        List<Object> titles = new ArrayList<>();
        //准备存储数据行
        List<Object> datas = new ArrayList<>();
        for (Object[] weekData : weekDatas) {
            titles.add(weekData[0]);
            datas.add(weekData[1]);
        }
        dataMap.put("titles",titles);
        dataMap.put("datas",datas);
        return dataMap;
    }
}
