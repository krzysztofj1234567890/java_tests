package com.example.concurrency;


public class ThreadLocalTest {
    private static final ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);
    private static final StringBuffer notThreadLocalValue = new StringBuffer() ;

    public static void main(String[] args) {
        Runnable task = () -> {
            int value = threadLocalValue.get();
            String value2 = notThreadLocalValue.toString() ;
            value += 1;
            value2 +=1 ;
            threadLocalValue.set(value);
            notThreadLocalValue.append(value2) ;
            System.out.println(Thread.currentThread().getName() + ": " + threadLocalValue.get()+" : "+notThreadLocalValue.toString());
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();
    }
}