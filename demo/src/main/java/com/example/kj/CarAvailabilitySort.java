package com.example.kj;

import java.util.Comparator;

public class CarAvailabilitySort implements Comparator<CarAvailability> {
    public int compare( CarAvailability a, CarAvailability b ) {
        if ( a.getFrom().isBefore( b.getFrom() ) ) return -1 ; 
        else if ( a.getFrom().isEqual( b.getFrom() )) {
            if ( a.getTo().isBefore( b.getTo() )) return -1 ;
            else if ( a.getTo().isEqual(b.getTo()) ) return 0 ;
            else return 1 ;
        } else {
            return 1 ;
        }
    }
}
