package com.zkk.service;

import com.zkk.entity.Car;
import com.zkk.utils.Pagination;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/23 23:45
 */
public interface CarService {
    Pagination getPage(Integer id, Integer current, Integer pageSize);

    void add(Car car);

    void update(Car car);
}
