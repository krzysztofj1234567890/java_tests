package com.example.kj;


public class CarFactory {
    public static Car createSedan( long carId, String registration ) {
        return new Car( carId, registration, Car.CarType.SEDAN ) ;
    }
    public static Car createSUV( long carId, String registration ) {
        return new Car( carId, registration, Car.CarType.SUV ) ;
    }
    public static Car createVan( long carId, String registration ) {
        return new Car( carId, registration, Car.CarType.VAN ) ;
    }
}