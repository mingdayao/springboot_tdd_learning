package com.ymd.learn.tdd;

import com.ymd.learn.tdd.model.Car;
import com.ymd.learn.tdd.repository.CarRepository;
import com.ymd.learn.tdd.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class CacheTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void caching() throws Exception {
        given(carRepository.findByName(anyString())).willReturn(new Car("prius", "Hybrid"));

        carService.getCarDetails("prius");
        carService.getCarDetails("prius");


        verify(carRepository, times(1)).findByName("prius");

    }



}
