package com.example.kj;

import com.example.kj.Car.CarType;

import java.time.LocalDate;
import java.util.* ;

public class CarRentalOffice {
    private List<CarAvailability> allCarsAvailability = new ArrayList<CarAvailability>() ;
    private Map<Long, Car> reservations = new HashMap<Long, Car> () ;

    /**
     * Add car managed by the office
     * @param car
     */
    public void addCar( Car car ) {
        LocalDate from = LocalDate.now() ;
        LocalDate to = from.plusDays(365);

        // make this car available for next 365 days
        this.allCarsAvailability.add( new CarAvailability( car, from, to ) ) ;
    }

    /**
     * Make a car reservation
     * @param personId
     * @param carType
     * @param fromDate
     * @param fromTime
     * @param numberOfDays
     * @return
     */
    public boolean makeReservation( Long personId, CarType carType, LocalDate fromDate, long fromTime, int numberOfDays ) {
        // check if the person alreeady has a reservation
        if ( reservations.containsKey( personId ) ) return false ;

        // can I make the reservation = find a car that is not reserved for this time period
        // Normally this would be a SQL query: SELECT registration, fromDate, toDate FROM CarAvailability WHERE carType =? AND 
        // However, I am not using a database so I just implement it
        boolean result = false ;
        LocalDate toDate = fromDate.plusDays(numberOfDays) ;
        for( CarAvailability availability : this.allCarsAvailability ) {
            if ( availability.isAvailable(carType, fromDate, toDate)) {

                // update car availability
                this.allCarsAvailability.remove( availability ) ;
                if ( availability.getFrom().isBefore( fromDate )) {
                    CarAvailability newAvailability = new CarAvailability( availability.getCar(), availability.getFrom(), fromDate ) ;
                    this.allCarsAvailability.add(newAvailability) ;
                }
                if ( availability.getTo().isAfter( toDate )) {
                    CarAvailability newAvailability = new CarAvailability( availability.getCar(), toDate, availability.getTo() ) ;
                    this.allCarsAvailability.add(newAvailability) ;
                }
                // make reservation
                this.reservations.put(personId, availability.getCar() ) ;
                result = true ;
                break ;
            }
        }
        return result ;
    }

    public boolean cancelReservation( long personId ) {
        return false ;
    }

    public Car rentACar( long personId ) {
        return reservations.get( personId ) ;
    }
}
