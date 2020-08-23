package com.zkk.dao.impl;

import com.zkk.dao.CarDao;
import com.zkk.entity.Car;
import org.springframework.stereotype.Repository;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/23 23:46
 */
@Repository
public class CarDaoImpl extends BaseDaoImpl<Car, Integer> implements CarDao {
}
