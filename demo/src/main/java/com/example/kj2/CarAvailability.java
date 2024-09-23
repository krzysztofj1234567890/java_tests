package com.example.kj2;
import java.time.LocalDateTime;

import com.example.kj2.Car.CarType;
public class CarAvailability {
    private Car car ;
    private LocalDateTime from ;
    private LocalDateTime to ;
    public CarAvailability(Car car, LocalDateTime from, LocalDateTime to) {
        this.car = car;
        this.from = from;
        this.to = to;
    }
    public Car getCar() {
        return car;
    }
    public LocalDateTime getFrom() {
        return from;
    }
    public LocalDateTime getTo() {
        return to;
    }

    public boolean isAvailable( CarType carType, LocalDateTime from, LocalDateTime to ) {
        if (  this.car.getType() != carType || this.from.isAfter( from ) || this.to.isBefore(to) ) {
            return false ;
        } else {
            return true ;
        }
    }

    
}
