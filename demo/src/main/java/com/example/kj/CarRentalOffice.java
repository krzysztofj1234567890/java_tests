package com.example.kj;

import com.example.kj.Car.CarType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.* ;

public class CarRentalOffice {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private List<CarAvailability> allCarsAvailability = new ArrayList<CarAvailability>() ;
    private Map<Long, Car> reservations = new HashMap<Long, Car> () ;

    public void addCar( Car car ) {
        LocalDate from = LocalDate.now() ;
        LocalDate to = from.plusDays(365);

        this.allCarsAvailability.add( new CarAvailability( car, from, to ) ) ;
    }
    /**
     * Make a reservation for a given car type and date fro..to
     * @param personId
     * @param car
     * @param from
     * @param to
     * @return true of reservation is success
     */
    public boolean makeReservation( Long personId, CarType carType, LocalDate fromDate, long fromTime, int numberOfDays ) {
        // check if the person alreeady has a reservation
        if ( reservations.containsKey( personId ) ) return false ;

        // can I make the reservation = find a car that is not reserved for this time period
        // Normally this would be a SQL query, but I am not using a database so I just implement it
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
