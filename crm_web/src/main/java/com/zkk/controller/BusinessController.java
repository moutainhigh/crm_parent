package com.zkk.controller;

import com.zkk.entity.CreditCard;
import com.zkk.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/23 22:21
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/card/addCard/{id}")
    public String addCard(@PathVariable Integer id, @RequestBody CreditCard creditCard){
        creditCard.setCustomerId(id);
        creditCardService.add(creditCard);
        return "ok";
    }
}
