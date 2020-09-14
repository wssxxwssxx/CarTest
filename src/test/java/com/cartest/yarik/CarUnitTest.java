package com.cartest.yarik;

import static org.assertj.core.api.Assertions.assertThat;

import com.cartest.yarik.model.Car;
import org.junit.Test;

class CarUnitTest {

    @Test
    public void whenCalledGetName_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        assertThat(car.getNumber()).isEqualTo("A239BF");
    }


    @Test
    public void whenCalledGetEmail_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        assertThat(car.getBrand()).isEqualTo("Tesla");
    }

    @Test
    public void whenCalledSetName_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        car.setNumber("A669BF");

        assertThat(car.getNumber()).isEqualTo("A669BF");
    }

    @Test
    public void whenCalledSetEmail_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        car.setBrand("Nissan");

        assertThat(car.getBrand()).isEqualTo("Nissan");
    }

    @Test
    public void whenCalledtoString_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");
        assertThat(car.toString()).isEqualTo("Car{id=0, number=A239BF, brand=Tesla}");
    }

}