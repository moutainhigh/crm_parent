package com.zkk.service.impl;

import com.zkk.dao.CarDao;
import com.zkk.entity.Car;
import com.zkk.service.CarService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/23 23:45
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public Pagination getPage(Integer id, Integer current, Integer pageSize) {
        return carDao.getPage("from Car where customerId = ?1",current,pageSize,id);
    }

    @Override
    public void add(Car car) {
        carDao.insert(car);
    }

    @Override
    public void update(Car car) {
        carDao.update(car);
    }
}
