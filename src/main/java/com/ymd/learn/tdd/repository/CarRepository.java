package com.ymd.learn.tdd.repository;

import com.ymd.learn.tdd.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByName(String name);
}
