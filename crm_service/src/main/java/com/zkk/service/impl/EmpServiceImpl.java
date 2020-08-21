package com.zkk.service.impl;

import com.zkk.dao.EmpDao;
import com.zkk.entity.Emp;
import com.zkk.entity.Role;
import com.zkk.entity.vo.EmpRoleVo;
import com.zkk.service.EmpService;
import com.zkk.utils.Pagination;
import com.zkk.utils.SpringMailUtils;
import com.zkk.utils.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 14:41
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Autowired
    private SpringMailUtils mailUtils;

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Override
    public Pagination getPage(Integer pageSize, Integer current) {
        return empDao.getPage("from Emp", current, pageSize);
    }

    @Override
    public void addEmp(final Emp emp) {
        //获取邮箱
        final String email = emp.getEmail();
        emp.setIsIce(0);
        //设置初始密码
        emp.setPassword("123456");
        if (!StringUtils.isEmpty(email)) {
            emp.setIsSend(0);
            threadPool.execute(new Runnable() {
                                   @Override
                                   public void run() {
                                       //初始化发送次数
                                       int count = 0;
                                       boolean isOk = false;
                                       while (count <= 3 && !isOk) {
                                           try {
                                               mailUtils.sendMail(email, "欢迎入职", "欢迎您：" + emp.getName() + ",你的账号为：" + emp.getAccount() + ",你的初始密码为" + emp.getPassword());
                                               isOk = true;//标识成功，结束循环
                                           } catch (Exception e) {
                                               //发送失败
                                               count++;
                                               e.printStackTrace();
                                               //优化，设置停顿时间
                                               try {
                                                   Thread.sleep(2000);
                                               } catch (InterruptedException e1) {
                                                   e1.printStackTrace();
                                               }
                                           }
                                       }
                                   }
                               }
            );
        }
        empDao.insert(emp);
        emp.setIsSend(1);
    }

    @Override
    public void updateEmp(Emp emp) {
        empDao.update(emp);
    }

    @Override
    public void updateRole(EmpRoleVo empRoleVo) {
        Emp emp = empDao.get(empRoleVo.getEmpId());
        List<Role> roleList = new ArrayList<Role>();
        Integer[] roleIds = empRoleVo.getRoleIds();
        for (Integer roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            roleList.add(role);
        }
        emp.setRoleList(roleList);
    }

    @Override
    public void iceEmp(Integer[] ids) {
        for (Integer id : ids) {
            empDao.update("update Emp set is_ice = 1 where id = ?1", id);
        }
    }

    @Override
    public void updateIsIce(Integer[] ids) {
        for (Integer id : ids) {
            empDao.update("update Emp set is_ice = 0 where id = ?1", id);
        }
    }

    @Override
    public Emp getEmpByUsername(String username) {
        return empDao.getEmpByUsername(username);
    }

    @Override
    public List<Emp> getEmpByDeptId(Integer id) {
        return empDao.getList("from Emp where dept_id = ?1", id);
    }

}
