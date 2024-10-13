package com.example.concurrency;

import java.util.*;
import java.util.function.* ;

public class LambdaExamples {
    public static void main(String[] args) { 
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        numbers.forEach( (n) -> { System.out.println(n); } );

        exercise1() ;
        exercise2() ;
    }

    // lambda expression to check if a given string is empty.
    static void exercise1() {
        Predicate<String> isEmptyString = str -> str.isEmpty();
        System.out.println( isEmptyString.test("ala") ) ;
    }

    // Sum 2 numbers
    static void exercise2() {
        Function<String, String> fun = s1 -> s1 + 2;
        System.out.println( fun.apply("ala") ) ;
        }
}
