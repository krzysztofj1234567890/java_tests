package com.example.kj;
public class Car {
    // different car types
    public enum CarType{ SEDAN, SUV, VAN } ;

    private Long carId ;
    private String registration ;
    private CarType type ;

    Car( long carId, String registration, CarType type ) {
        this.carId = carId ;
        this.registration = registration ;
        this.type = type ;
    }

    public Long getCarId() {
        return this.carId ;
    }
    public String getRegistration() {
        return this.registration ;
    }

    public CarType getType() {
        return type;
    }

}