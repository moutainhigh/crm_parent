package com.zkk.service;

import com.zkk.entity.House;
import com.zkk.utils.Pagination;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/24 0:39
 */
public interface HouseService {
    Pagination getHousePage(Integer id, Integer current, Integer pageSize);

    void addHouse(House house);
}
