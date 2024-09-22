package com.example.kj;
import java.time.LocalDate;

import com.example.kj.Car.CarType;
public class CarAvailability {
    private Car car ;
    private LocalDate from ;
    private LocalDate to ;
    public CarAvailability(Car car, LocalDate from, LocalDate to) {
        this.car = car;
        this.from = from;
        this.to = to;
    }
    public Car getCar() {
        return car;
    }
    public LocalDate getFrom() {
        return from;
    }
    public LocalDate getTo() {
        return to;
    }

    public boolean isAvailable( CarType carType, LocalDate from, LocalDate to ) {
        if (  this.car.getType() != carType || this.from.isAfter( from ) || this.to.isBefore(to) ) {
            return false ;
        } else {
            return true ;
        }
    }

    
}
