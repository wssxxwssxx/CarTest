package com.cartest.yarik;

import com.cartest.yarik.controller.CarController;
import com.cartest.yarik.model.Car;
import com.cartest.yarik.repository.CarRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public class CarControllerUnitTest {
    private static CarController carController;
    private static CarRepository mockedCarRepository;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeClass
    public static void setUpUserControllerInstance() {
        mockedCarRepository = mock(CarRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        carController = new CarController(mockedCarRepository);
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        assertThat(carController.showSignUpForm(car)).isEqualTo("add-car");
    }

    @Test
    public void whenCalledaddUserAndValidUser_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(carController.addCar(car, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledaddUserAndInValidUser_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(carController.addCar(car, mockedBindingResult, mockedModel)).isEqualTo("add-car");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledshowUpdateForm_thenIllegalArgumentException() {
        assertThat(carController.showUpdateForm(0, mockedModel)).isEqualTo("update-car");
    }

    @Test
    public void whenCalledupdateUserAndValidUser_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(carController.updateCar(1l, car, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledupdateUserAndInValidUser_thenCorrect() {
        Car car = new Car("2020","Green","Tesla","A239BF");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(carController.updateCar(1l, car, mockedBindingResult, mockedModel)).isEqualTo("update-car");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalleddeleteUser_thenIllegalArgumentException() {
        assertThat(carController.deleteCar(1l, mockedModel)).isEqualTo("index");
    }
}
