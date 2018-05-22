package com.ymd.learn.tdd.service;

import com.ymd.learn.tdd.exception.CarNotFoundException;
import com.ymd.learn.tdd.model.Car;
import com.ymd.learn.tdd.repository.CarRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Cacheable("cars")
    public Car getCarDetails(String name) {
        Car car = carRepository.findByName(name);
        if(car == null) throw new CarNotFoundException();
        return car;
    }
}
