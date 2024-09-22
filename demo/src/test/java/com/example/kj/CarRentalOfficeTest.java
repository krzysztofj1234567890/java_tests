package com.example.kj;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CarRentalOfficeTest {
    private static CarRentalOffice office = new CarRentalOffice() ; 

    @Test
    public void rentSedanVanSUV() {
        office.addCar( CarFactory.createSedan(1, "sedan" ) ) ;
        office.addCar( CarFactory.createSUV(2, "suv" ) ) ;
        office.addCar( CarFactory.createVan(3, "van" ) ) ;

        // Create person
        Person personJohn = new Person( 1, "John") ;
        Person personAdam = new Person( 2, "Adam") ;
        Person personKate = new Person( 3, "Kate") ;

        // Person makes reservation
        office.makeReservation(personJohn.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-10-04"), 10, 5 );
        office.makeReservation(personAdam.getPersonId(), Car.CarType.SUV, LocalDate.parse("2024-10-04"), 10, 5 );
        office.makeReservation(personKate.getPersonId(), Car.CarType.VAN, LocalDate.parse("2024-10-04"), 10, 5 );

        // Person rents a car
        Car car1 = office.rentACar(personJohn.getPersonId() ) ;
        assertTrue( car1!= null );

        Car car2 = office.rentACar(personAdam.getPersonId() ) ;
        assertTrue( car2!= null );

        Car car3 = office.rentACar(personKate.getPersonId() ) ;
        assertTrue( car3!= null );
    }

    @Test
    public void rentSameCarDifferentDates() {
        office.addCar( CarFactory.createSedan(1, "sedan" ) ) ;

        // Create person
        Person personJohn = new Person( 1, "John") ;
        Person personAdam = new Person( 2, "Adam") ;
        Person personKate = new Person( 3, "Kate") ;

        // Person makes reservation
        office.makeReservation(personJohn.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), 4, 5 );
        office.makeReservation(personAdam.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-10"), 4, 5 );
        office.makeReservation(personKate.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-20"), 4, 5 );

        // Person rents a car
        Car car1 = office.rentACar(personJohn.getPersonId() ) ;
        assertTrue( car1!= null );

        Car car2 = office.rentACar(personAdam.getPersonId() ) ;
        assertTrue( car2!= null );

        Car car3 = office.rentACar(personKate.getPersonId() ) ;
        assertTrue( car3!= null );
    }

    @Test
    public void rentSameCarTypeSameDates() {
        office.addCar( CarFactory.createSedan(1, "sedan" ) ) ;

        // Create person
        Person personJohn = new Person( 1, "John") ;
        Person personAdam = new Person( 2, "Adam") ;
        Person personKate = new Person( 3, "Kate") ;

        // Person makes reservation
        office.makeReservation(personJohn.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), 4, 5 );
        office.makeReservation(personAdam.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), 4, 5 );
        office.makeReservation(personKate.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), 4, 5 );

        // Person rents a car
        Car car1 = office.rentACar(personJohn.getPersonId() ) ;
        assertTrue( car1!= null );

        Car car2 = office.rentACar(personAdam.getPersonId() ) ;
        assertTrue( car2!= null );

        Car car3 = office.rentACar(personKate.getPersonId() ) ;
        assertTrue( car3!= null );
    }

    @Test
    public void noVanAvailable() {
        office.addCar( CarFactory.createSedan(1, "AWS-001" ) ) ;

        // Create person
        Person person = new Person( 1, "John") ;

        // Person makes reservation
        office.makeReservation(person.getPersonId(), Car.CarType.VAN, LocalDate.parse("2024-10-04"), 10, 5 );

        // Person rents a car
        Car car = office.rentACar(person.getPersonId() ) ;
        assertTrue( car== null );
    }


    @Test
    public void testPerformance() {
        int carCount = 100 ;
        // add many cars
        for ( int i=1; i<=carCount; i++ ) {
            office.addCar( CarFactory.createSedan(i, "AWS-"+i ) ) ;
        }

        // Create people
        List<Person> people = new ArrayList<Person>() ;
        for( int i=1; i<=100*carCount; i++ ) {
            Person person = new Person( i, "Name "+i) ;
            people.add( person ) ;
        }

        // People make reservation
        int dayCounter = 0 ;
        Iterator<Person> pIterator = people.iterator() ;
        while( pIterator.hasNext() ) {
            Person person = pIterator.next() ;
            LocalDate from = LocalDate.parse("2024-11-01").plusDays( dayCounter ) ;
            LocalDate to = from.plusDays( 2 ) ;
            office.makeReservation(person.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), 10, 1 );

            dayCounter = dayCounter + 2 ;
            if ( dayCounter > 300 ) {
                dayCounter = 0 ;
            }
        }

        // Each person should have a car
        pIterator = people.iterator() ;
        while( pIterator.hasNext() ) {
            Person person = pIterator.next() ;
            Car car = office.rentACar(person.getPersonId() ) ;
            assertTrue( car!= null );
        }
    }
}
