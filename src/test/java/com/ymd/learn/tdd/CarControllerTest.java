package com.ymd.learn.tdd;

import com.ymd.learn.tdd.controller.CarController;
import com.ymd.learn.tdd.exception.CarNotFoundException;
import com.ymd.learn.tdd.model.Car;
import com.ymd.learn.tdd.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;


    @Test
    public void getCar_ShouldReturnCar() throws Exception {
        given(carService.getCarDetails(anyString())).willReturn(new Car("prius", "hybrid"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prius"))
                .andExpect(jsonPath("type").value("hybrid"));
    }


    @Test
    public void getCar_NotFound() throws Exception {
        given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isNotFound());
    }







}
