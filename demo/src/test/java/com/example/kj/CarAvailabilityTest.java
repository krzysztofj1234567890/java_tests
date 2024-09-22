package com.example.kj;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;

import org.junit.Test;

import com.example.kj.Car.CarType;

/**
 * Unit test for simple App.
 */
public class CarAvailabilityTest {
    @Test
    public void fullyIncluded() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDate from = LocalDate.now().plusDays(10) ;
        LocalDate to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDate fromTest = LocalDate.now().plusDays(11) ;
        LocalDate toTest = fromTest.plusDays(2) ;
        assertTrue( ca.isAvailable(car.getType(), fromTest, toTest) );
    }

    @Test
    public void wrongCarType() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDate from = LocalDate.now().plusDays(10) ;
        LocalDate to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDate fromTest = LocalDate.now().plusDays(11) ;
        LocalDate toTest = fromTest.plusDays(2) ;
        assertFalse( ca.isAvailable(CarType.SUV, fromTest, toTest) );
    }

    @Test
    public void fullyOverlaps() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDate from = LocalDate.now().plusDays(10) ;
        LocalDate to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDate fromTest = LocalDate.now().plusDays(5) ;
        LocalDate toTest = fromTest.plusDays(10) ;
        assertFalse( ca.isAvailable(car.getType(), fromTest, toTest) );
    }

    @Test
    public void noOverlap() {
        Car car = CarFactory.createSedan(1, "TEST" ) ;
        LocalDate from = LocalDate.now().plusDays(10) ;
        LocalDate to = from.plusDays(10) ;
        CarAvailability ca = new CarAvailability(car, from, to ) ;

        LocalDate fromTest = LocalDate.now().plusDays(5) ;
        LocalDate toTest = fromTest.plusDays(2) ;
        assertFalse( ca.isAvailable(car.getType(), fromTest, toTest) );
    }
}
