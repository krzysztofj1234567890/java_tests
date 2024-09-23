package com.example.kj2;

public class CarReservation {
    private Car car ;
    private Long personId ;
    public CarReservation(Car car, long personId) {
        this.car = car;
        this.personId = personId;
    }
    public Car getCar() {
        return this.car;
    }
    public Long getPersonId() {
        return this.personId;
    }

    
}
