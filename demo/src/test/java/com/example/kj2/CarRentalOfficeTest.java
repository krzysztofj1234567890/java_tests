package com.example.kj2;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CarRentalOfficeTest {
    private static CarRentalOffice office = new CarRentalOffice() ; 

    @Before
    public void initTest() {
        CarRentalOfficeTest.office = new CarRentalOffice() ;
    }

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
        LocalTime time = LocalTime.now().withHour(10).withMinute(0).withSecond(0) ;
        office.makeReservation(personJohn.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-10-04"), time, 5 );
        office.makeReservation(personAdam.getPersonId(), Car.CarType.SUV, LocalDate.parse("2024-10-04"), time, 5 );
        office.makeReservation(personKate.getPersonId(), Car.CarType.VAN, LocalDate.parse("2024-10-04"), time, 5 );

        // Person rents a car
        CarReservation car1 = office.rentACar(personJohn.getPersonId() ) ;
        assertTrue( car1!= null );

        CarReservation car2 = office.rentACar(personAdam.getPersonId() ) ;
        assertTrue( car2!= null );

        CarReservation car3 = office.rentACar(personKate.getPersonId() ) ;
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
        LocalTime time = LocalTime.now().withHour(10).withMinute(0).withSecond(0) ;
        office.makeReservation(personJohn.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), time, 5 );
        office.makeReservation(personAdam.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-10"), time, 5 );
        office.makeReservation(personKate.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-20"), time, 5 );

        // Person rents a car
        CarReservation car1 = office.rentACar(personJohn.getPersonId() ) ;
        assertTrue( car1!= null );

        CarReservation car2 = office.rentACar(personAdam.getPersonId() ) ;
        assertTrue( car2!= null );

        CarReservation car3 = office.rentACar(personKate.getPersonId() ) ;
        assertTrue( car3!= null );
    }

    @Test
    public void rentSameCarTypeSameDates() {
        office.addCar( CarFactory.createSedan(1, "sedan" ) ) ;
        office.addCar( CarFactory.createSedan(2, "sedan" ) ) ;
        office.addCar( CarFactory.createSedan(3, "sedan" ) ) ;

        // Create person
        Person personJohn = new Person( 1, "John") ;
        Person personAdam = new Person( 2, "Adam") ;
        Person personKate = new Person( 3, "Kate") ;

        // Person makes reservation
        LocalTime time = LocalTime.now().withHour(10).withMinute(0).withSecond(0) ;
        office.makeReservation(personJohn.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), time, 5 );
        office.makeReservation(personAdam.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), time, 5 );
        office.makeReservation(personKate.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), time, 5 );

        // Person rents a car
        CarReservation car1 = office.rentACar(personJohn.getPersonId() ) ;
        assertTrue( car1!= null );

        CarReservation car2 = office.rentACar(personAdam.getPersonId() ) ;
        assertTrue( car2!= null );

        CarReservation car3 = office.rentACar(personKate.getPersonId() ) ;
        assertTrue( car3!= null );
    }

    @Test
    public void noCarsAvailable() {
        office.addCar( CarFactory.createSedan(1, "sedan" ) ) ;
        office.addCar( CarFactory.createSedan(2, "sedan" ) ) ;

        // Create person
        Person personJohn = new Person( 1, "John") ;
        Person personAdam = new Person( 2, "Adam") ;
        Person personKate = new Person( 3, "Kate") ;

        // Person makes reservation
        LocalTime time = LocalTime.now().withHour(10).withMinute(0).withSecond(0) ;
        office.makeReservation(personJohn.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), time, 5 );
        office.makeReservation(personAdam.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), time, 5 );
        CarReservation reservation = office.makeReservation(personKate.getPersonId(), Car.CarType.SEDAN, LocalDate.parse("2024-11-01"), time, 5 );

        assertTrue( reservation == null );
    }

    @Test
    public void noVanAvailable() {
        office.addCar( CarFactory.createSedan(1, "AWS-001" ) ) ;

        // Create person
        Person person = new Person( 1, "John") ;

        // Person makes reservation
        LocalTime time = LocalTime.now().withHour(10).withMinute(0).withSecond(0) ;
        office.makeReservation(person.getPersonId(), Car.CarType.VAN, LocalDate.parse("2024-10-04"), time, 5 );

        // Person rents a car
        CarReservation car = office.rentACar(person.getPersonId() ) ;
        assertTrue( car== null );
    }

    @Test
    public void testMany() {
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
        LocalTime time = LocalTime.now().withHour(10).withMinute(0).withSecond(0) ;
        int dayCounter = 0 ;
        Iterator<Person> pIterator = people.iterator() ;
        while( pIterator.hasNext() ) {
            Person person = pIterator.next() ;
            LocalDate from = LocalDate.parse("2024-11-01").plusDays( dayCounter ) ;
            CarReservation reservation= office.makeReservation(person.getPersonId(), Car.CarType.SEDAN, from, time, 1 );
            assertTrue( reservation != null);

            dayCounter = dayCounter + 2 ;
            if ( dayCounter > 300 ) {
                dayCounter = 0 ;
            }
        }

        // Each person should have a car
        pIterator = people.iterator() ;
        while( pIterator.hasNext() ) {
            Person person = pIterator.next() ;
            CarReservation car = office.rentACar(person.getPersonId() ) ;
 //           System.out.println( person.getPersonId()) ;
            assertTrue( car!= null );
        }
    }

}
