package com.example.concurrency;

// v1
/*/
public class RunnableDemo {
    public static void main(String[] args) { 
        new Thread(new GFGRun()).start(); 
        System.out.println( "Running in main thread");
    } 
}

class GFGRun implements Runnable { 
    public void run() { 
        System.out.println( "Running in Runnable thread");
    } 
} 
*/

// v2
/*
public class RunnableDemo {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println( "Running in Runnable thread");
            }
        };
 
        r.run();
        System.out.println( "Running in main thread");
    }
}
*/

// v3 - best
public class RunnableDemo {
    public static void main(String[] args) {
        Runnable r  = ()-> System.out.println( "Running in Runnable thread");
         r.run();
         System.out.println( "Running in main thread");
    }
}

