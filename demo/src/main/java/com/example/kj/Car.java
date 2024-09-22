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

    public boolean isSedan() {
        if ( this.type == CarType.SEDAN ) return true ;
        else return false ;
    }
    public String getRegistration() {
        return this.registration ;
    }

}