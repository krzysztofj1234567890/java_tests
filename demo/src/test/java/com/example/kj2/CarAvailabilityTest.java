package com.example.kj2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;

import org.junit.Test;

import com.example.kj2.Car.CarType;

/**
 * Unit test for simple App.
 */
public class CarAvailabilityTest {
    @Test
    public void fullyIncluded() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDateTime from = LocalDateTime.now().plusDays(10) ;
        LocalDateTime to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDateTime fromTest = LocalDateTime.now().plusDays(11) ;
        LocalDateTime toTest = fromTest.plusDays(2) ;
        assertTrue( ca.isAvailable(car.getType(), fromTest, toTest) );
    }

    @Test
    public void wrongCarType() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDateTime from = LocalDateTime.now().plusDays(10) ;
        LocalDateTime to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDateTime fromTest = LocalDateTime.now().plusDays(11) ;
        LocalDateTime toTest = fromTest.plusDays(2) ;
        assertFalse( ca.isAvailable(CarType.SUV, fromTest, toTest) );
    }

    @Test
    public void fullyOverlaps() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDateTime from = LocalDateTime.now().plusDays(10) ;
        LocalDateTime to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDateTime fromTest = LocalDateTime.now().plusDays(5) ;
        LocalDateTime toTest = fromTest.plusDays(10) ;
        assertFalse( ca.isAvailable(car.getType(), fromTest, toTest) );
    }

    @Test
    public void noOverlap() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDateTime from = LocalDateTime.now().plusDays(10) ;
        LocalDateTime to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDateTime fromTest = LocalDateTime.now().plusDays(5) ;
        LocalDateTime toTest = fromTest.plusDays(2) ;
        assertFalse( ca.isAvailable(car.getType(), fromTest, toTest) );
    }
}
