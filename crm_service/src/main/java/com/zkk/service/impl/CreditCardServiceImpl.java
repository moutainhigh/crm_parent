package com.zkk.service.impl;

import com.zkk.dao.CreditCardDao;
import com.zkk.entity.CreditCard;
import com.zkk.service.CreditCardService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/23 22:29
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardDao creditCardDao;

    @Override
    public void add(CreditCard creditCard) {
        creditCardDao.insert(creditCard);
    }

    @Override
    public Pagination getCardPage(Integer id, Integer current, Integer pageSize) {
        return creditCardDao.getPage("from CreditCard where customerId = ?1",current,pageSize,id);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            creditCardDao.delete(id);
        }
    }

}
