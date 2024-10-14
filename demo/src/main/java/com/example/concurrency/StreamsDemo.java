package com.example.concurrency;

import java.util.*;
import java.util.stream.* ;
import java.util.function.*;
import java.math.BigDecimal;

public class StreamsDemo {
    public static void main( String[] args ) {
        List<String> list = Arrays.asList("d", "e", "a", "b", "c", "a", "b");

        // Using Stream map(Function mapper) to convert the Strings in stream to UpperCase form
        System.out.println( "--- map 1") ;
        List<String> answer = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(answer);

        System.out.println( "--- filter 1") ;
        answer = list.stream().filter( c -> c != "a").collect(Collectors.toList());
        System.out.println(answer);

        System.out.println( "--- sorted 1") ;
        answer = list.stream().sorted().collect(Collectors.toList());
        System.out.println(answer);

        System.out.println( "--- flatMap 1") ;
        List<List<String>> listOfLists = Arrays.asList(
            Arrays.asList("Geeks", "For"),
            Arrays.asList("GeeksForGeeks", "A computer portal"),
            Arrays.asList("Java", "Programming")
        );
        answer = listOfLists.stream().flatMap( aa -> aa.stream()).collect(Collectors.toList());
        System.out.println(answer);

        System.out.println( "--- Test 1") ;
        System.out.println( exercise1( new Integer[] {10,13,8,21,23,3,15},3 ) ) ;

        exercise2() ;
        exercise3() ;
        exercise4() ;
        exercise5() ;
        exercise6() ;
        exercise7() ;
        exercise8() ;
        exercise9() ;
        exercise10() ;
        exercise11() ;
        exercise12() ;
    }

    static int exercise1( Integer[] values, int k ) {
        List<Integer> listI = Arrays.asList( values ) ;
        while( k >0 ) {
            listI = listI.stream()
                .sorted( (i1,i2) -> Integer.compare(i2,i1))
                .map( i3 -> i3 * 2 )
                .collect( Collectors.toList()) ;
            k -- ;
        }
        System.out.println(listI);
        return listI.stream().reduce(0, (a,b) -> a+b) ;

    }

    // How would you count the frequency of characters in a String using Streams?
    static void exercise2() {
        String input = "java8stream";
        Map<Character, Long> frequency = input.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(frequency);
    }

    // How can you find the second highest number in a list?
    static void exercise3() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> secondHighest = numbers.stream()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst();
        System.out.println(secondHighest);
    }

    // How would you flatten a list of lists in Java 8?
    static void exercise4() {
        List<List<String>> numbers = Arrays.asList(
            Arrays.asList("Geeks", "For"),
            Arrays.asList("GeeksForGeeks", "A computer portal"),
            Arrays.asList("Java", "Programming")
        );
        List<String> result = numbers.stream()
            .flatMap( aa -> aa.stream() )
            .collect( Collectors.toList()) ;
        System.out.println(result);
    }

    // How can you remove duplicates from a list?
    static void exercise5() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6,3,4,5,6);
        List<Integer> result = numbers.stream().distinct().collect( Collectors.toList() ) ;
        System.out.println(result);
    }

    // How would you sum a list of integers using Streams?
    static void exercise6() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6,3,4,5,6);
        Integer result = numbers.stream().distinct().reduce(0, (a,b) -> a+b ) ;
        System.out.println(result);
    }

    // How can you convert a list of strings to uppercase?
    static void exercise7() {
        List<String> numbers = Arrays.asList("as", "os", "aD");
        List<String>  result = numbers.stream().distinct().map( String::toUpperCase).collect( Collectors.toList()) ;
        System.out.println(result);
    }

    // How would you sort a list of strings by length?
    static void exercise8() {
        System.out.println( "----- 8. How would you sort a list of strings by length?" );
        List<String> numbers = Arrays.asList("asdsa", "oswe", "aDw");
        List<String>  result = numbers.stream().sorted( Comparator.comparingInt(String::length)).collect( Collectors.toList()) ;;
        System.out.println(result);
    }

    // How can you check if all elements in a list are even?
    static void exercise9() {
        System.out.println( "----- 9. How can you check if all elements in a list are even?" );
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        boolean  result = numbers.stream().allMatch(n -> n % 2 == 0);
        System.out.println(result);
    }

    // How can you find the average of a list of numbers?
    static void exercise10() {
        System.out.println( "----- 10. How can you find the average of a list of numbers?" );
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        double  result = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(result);
    }

    // Write a stream operation that filters out all even numbers from a list and then maps each remaining number to its square
    static void exercise11() {
        System.out.println( "----- 11. Write a stream operation that filters out all even numbers from a list and then maps each remaining number to its square" );
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> result = numbers.stream().filter( c -> c %2 == 0).map( a -> a*a ).collect( Collectors.toList()) ;
        System.out.println(result);
    }

    // Group by the name + Count or Sum the Qty.
    static void exercise12() {
        System.out.println( "----- 12. Group by the name + Count or Sum the Qty" );
        //3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        Map<String, Long> counting = items.stream().collect( Collectors.groupingBy(Item::getName, Collectors.counting()));
        System.out.println(counting);

        Map<String, Integer> result = items.stream().collect( Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println(result);
    }    
}

class Item {

    private String name;
    private int qty;
    private BigDecimal price;
    public Item(String name, int qty, BigDecimal price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getQty() {
        return qty;
    }
    public BigDecimal getPrice() {
        return price;
    }
    
}