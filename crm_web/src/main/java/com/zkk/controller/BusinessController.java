package com.zkk.controller;

import com.zkk.entity.Car;
import com.zkk.entity.CreditCard;
import com.zkk.service.CarService;
import com.zkk.service.CreditCardService;
import com.zkk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private CarService carService;

    @PostMapping("/card/addCard/{id}")
    public String addCard(@PathVariable Integer id, @RequestBody CreditCard creditCard) {
        creditCard.setCustomerId(id);
        creditCardService.add(creditCard);
        return "ok";
    }

    @GetMapping("/card/page/{id}/{current}/{pageSize}")
    public Pagination getCardPage(@PathVariable Integer id, @PathVariable Integer current, @PathVariable Integer pageSize) {
        return creditCardService.getCardPage(id, current, pageSize);
    }

    @PostMapping("/card/deleteCard")
    public String deleteCard(@RequestBody Integer id[]) {
        creditCardService.delete(id);
        return "ok";
    }

    @GetMapping("/car/page/{id}/{current}/{pageSize}")
    public Pagination getCarPage(@PathVariable Integer id, @PathVariable Integer current, @PathVariable Integer pageSize) {
        return carService.getPage(id, current, pageSize);
    }

    @PostMapping("/car/addCar/{id}")
    public String addCar(@PathVariable Integer id,@RequestBody Car car){
        car.setCustomerId(id);
        carService.add(car);
        return "ok";
    }

    @PostMapping("/car/updateCar")
    public String updateCar(@RequestBody Car car){
        carService.update(car);
        return "ok";
    }
}
