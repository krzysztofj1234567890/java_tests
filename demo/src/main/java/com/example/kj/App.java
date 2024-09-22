package com.example.kj;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TASK: 
 * implement a simulated Car Rental system using object-oriented principles. 
    1. Recommended time for this task is 2-4 hours.
    2. You can use any language you prefer for this exercise.
    3. The system should allow reservation of a car of a given type at a desired date and time for a given number of days. 
    4. There are 3 types of cars (sedan, SUV and van).
    5. The number of cars of each type is limited.
    6. Use unit tests to prove the system satisfies the requirements. 
    7. Please be prepared to discuss the design and implementation during the interview.
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "START!" );
        
        // Create rental office and add cars to it
        CarRentalOffice office = new CarRentalOffice() ;
        office.addCar( CarFactory.createSedan(1, "AWS-001" ) ) ;

        // Create person
        Person person = new Person( 1, "John") ;

        // Person makes reservation
        office.makeReservation(person.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-10-04"), 10, 5 );

        // Person rents a car
        Car car = office.rentACar(person.getPersonId() ) ;

        System.out.println( String.format( "rented car type:%s registration:%s", car.getType(), car.getRegistration() )) ;


 
    }
}
