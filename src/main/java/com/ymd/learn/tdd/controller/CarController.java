package com.ymd.learn.tdd.controller;

import com.ymd.learn.tdd.exception.CarNotFoundException;
import com.ymd.learn.tdd.model.Car;
import com.ymd.learn.tdd.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("/cars/{name}")
    public Car getCar(@PathVariable String name) {
        return this.carService.getCarDetails(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void carNotFoundHandler(CarNotFoundException ex) {}
}
