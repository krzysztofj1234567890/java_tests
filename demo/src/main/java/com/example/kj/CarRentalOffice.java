package com.example.kj;

import com.example.kj.Car.CarType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.* ;

public class CarRentalOffice {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    private OrderedMap<String,

    private Map<String, Car> sedanMap = new HashMap<String, Car>() ;
    private Map<Long, CarType> reservations = new HashMap<Long, CarType> () ;
    private Map<String, Integer> dayCarMap = new HashMap<String, Integer>() ;
    private int sedanCounter = 0 ;

    public CarRentalOffice addCar( Car car ) {
        if ( car.isSedan() ) { 
            this.sedanMap.put( car.getRegistration(), car ) ;
            this.sedanCounter ++ ;
        }
        return this ;
    }
    /**
     * Make a reservation for a given car type and date fro..to
     * @param personId
     * @param car
     * @param from
     * @param to
     * @return true of reservation is success
     */
    public boolean makeReservation( Long personId, CarType type, LocalDate fromDate, long fromTime, int numberOfDays ) {
        // check if the person alreeady has a reservation
        if ( reservations.containsKey( personId ) ) return false ;

        // can I make the reservation = find a car that is not reserved for this time period

        // reserve days        
        for(  int i=0; i<numberOfDays; i++ ) {
            LocalDate tmpDate = fromDate.plusDays(1) ;
            String dayCarType = tmpDate.format( formatter )+type ;
            int value = 0 ;
            if ( this.dayCarTypeCounterMap.containsKey( dayCarType)) {
                value = this.dayCarTypeCounterMap.get(dayCarType) ;
            }
            value ++ ;
        }

        // add car to person
        reservations.put( personId, type ) ;
        return false ;
    }

    public boolean cancelReservation( long personId ) {
        return false ;
    }

    public Car rentACar( ) {
        return null ;
    }
}
