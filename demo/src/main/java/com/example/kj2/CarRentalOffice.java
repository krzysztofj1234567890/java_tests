package com.example.kj2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.* ;


public class CarRentalOffice {
    // availability of all cars
    private List<CarAvailability> allCarsAvailability = new ArrayList<CarAvailability>() ;

    // key is personId and value is CarSeservation
    private Map<Long, CarReservation> reservations = new HashMap<Long, CarReservation> () ;

    public void addCar( Car car ) {
        LocalDateTime from = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = from.plusDays(365);

        // make this car available for next 365 days
        this.allCarsAvailability.add( new CarAvailability( car, from, to ) ) ;
    }
    public CarReservation makeReservation( Long personId, Car.CarType carType, LocalDate from, LocalTime time, int numberOdDays) {
        LocalDateTime newFrom = LocalDateTime.now().withYear(from.getYear())
            .withMonth( from.getMonthValue())
            .withDayOfMonth( from.getDayOfMonth())
            .withHour(time.getHour())
            .withMinute( time.getMinute())
            .withSecond( time.getSecond() );
        LocalDateTime newTo = newFrom.plusDays( numberOdDays ) ;

        // check if the person already has a reservation
        if ( reservations.containsKey( personId ) ) return reservations.get( personId ) ;
        
        // can I make the reservation = find a car that is not reserved for this time period
        // Normally this would be a SQL query: SELECT registration, fromDate, toDate FROM CarAvailability WHERE carType =? AND fromDate<? AND toDate <?
        // However, I am not using a database so I just implement it
        CarReservation result = null ;
        for( CarAvailability availability : this.allCarsAvailability ) {
            if ( availability.isAvailable(carType, newFrom, newTo)) {

                // update car availability
                this.allCarsAvailability.remove( availability ) ;
                if ( availability.getFrom().isBefore( newFrom )) {
                    CarAvailability newAvailability = new CarAvailability( availability.getCar(), availability.getFrom(), newFrom ) ;
                    this.allCarsAvailability.add(newAvailability) ;
                }
                if ( availability.getTo().isAfter( newTo )) {
                    CarAvailability newAvailability = new CarAvailability( availability.getCar(), newTo, availability.getTo() ) ;
                    this.allCarsAvailability.add(newAvailability) ;
                }
                // make reservation
                result = new CarReservation( availability.getCar(), personId ) ;
                this.reservations.put(personId, result ) ;

                break ;
            }
        }
        return result ;
    }       

    public CarReservation rentACar( long personId ) {
        return reservations.get( personId ) ;
    }
}
