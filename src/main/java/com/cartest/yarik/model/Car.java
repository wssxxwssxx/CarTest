package com.cartest.yarik.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Car {

    public Car(@NotBlank String year, @NotBlank String color, @NotBlank String brand, @NotBlank String number) {
        this.year = year;
        this.color = color;
        this.brand = brand;
        this.number = number;
    }

    public Car() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String year;

    @NotBlank
    private String color;

    @NotBlank
    private String brand;

    @NotBlank
    private String number;

    @NotNull
    @CreationTimestamp
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
