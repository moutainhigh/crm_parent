package com.zkk.service.impl;

import com.zkk.dao.HouseDao;
import com.zkk.entity.House;
import com.zkk.service.HouseService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/24 0:39
 */
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Override
    public Pagination getHousePage(Integer id, Integer current, Integer pageSize) {
        return houseDao.getPage("from House where customerId = ?1",current,pageSize,id);
    }

    @Override
    public void addHouse(House house) {
        houseDao.insert(house);
    }
}
