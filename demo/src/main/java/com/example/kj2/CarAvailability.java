package com.example.kj2;
import java.time.LocalDateTime;

import com.example.kj2.Car.CarType;
public class CarAvailability implements Comparable<CarAvailability>{
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
    @Override
    public int compareTo(CarAvailability o) {
        if ( this.from.isBefore( o.from) ) {
            return 1 ;
        } else if ( this.from.isAfter( o.from)) {
            return -1 ;
        } else {
            if ( this.to.isBefore( o.to) ) {
                return 1 ;
            } else if ( this.to.isAfter( o.to) ) {
                return -1 ;
            } else {
                return this.car.getCarId().compareTo( o.car.getCarId() ) ;
            }
        }
    }

    
}
