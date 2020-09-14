package com.cartest.yarik.controller;

import com.cartest.yarik.repository.CarRepository;
import com.cartest.yarik.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CarController {

    private CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @GetMapping("/signup")
    @Cacheable("car")
    public String showSignUpForm(Car car){
        return "add-car";
    }

    @PostMapping("/addcar")
    @Cacheable(value = "car", key = "#car.number")
    public String addCar(@Valid Car car, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-car";
        }

        carRepository.save(car);
        model.addAttribute("cars", carRepository.findAll());
        return "index";

    }

    @Cacheable("car")
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));

        model.addAttribute("car",car);
        return "update-car";

    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, @Valid Car car,
                            BindingResult result, Model model){

        if(result.hasErrors()){
            car.setId(id);
            return "update-car";
        }

        carRepository.save(car);
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    @CacheEvict("car")
    public String deleteCar(@PathVariable("id") long id, Model model){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        carRepository.delete(car);
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }

    @PostMapping("/car")
    public String filter(@RequestParam String filter, Model model){
        model.addAttribute("cars", carRepository.findByNumberStartingWith(filter));
        model.addAttribute("cars", carRepository.findByNumberEndingWith(filter));
        model.addAttribute("cars", carRepository.findByNumberContaining(filter));
        return "index";
    }

}
