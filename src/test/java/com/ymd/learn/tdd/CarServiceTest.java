package com.ymd.learn.tdd;

import com.ymd.learn.tdd.exception.CarNotFoundException;
import com.ymd.learn.tdd.model.Car;
import com.ymd.learn.tdd.repository.CarRepository;
import com.ymd.learn.tdd.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;


    @Test
    public void getCar_ShouldReturnCar() {
        given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));

        Car car = carService.getCarDetails("prius");

        assertThat(car.getName()).isEqualTo("prius");
        assertThat(car.getType()).isEqualTo("hybrid");
    }


    @Test(expected = CarNotFoundException.class)
    public void getCar_NotFound() {
        given(carService.getCarDetails("prius")).willReturn(null);

        carService.getCarDetails("prius");
    }



}