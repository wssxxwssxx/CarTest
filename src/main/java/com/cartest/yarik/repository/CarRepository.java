package com.cartest.yarik.repository;

import com.cartest.yarik.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByNumberStartingWith(String number);
    List<Car> findByNumberEndingWith(String number);
    List<Car> findByNumberContaining(String number);
}
