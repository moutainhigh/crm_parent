package com.zkk.service;

import com.zkk.entity.CreditCard;
import com.zkk.utils.Pagination;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/23 22:29
 */
public interface CreditCardService {
    void add(CreditCard creditCard);

    Pagination getCardPage(Integer id, Integer current, Integer pageSize);

    void delete(Integer[] id);
}
