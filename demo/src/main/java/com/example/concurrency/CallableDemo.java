package com.example.concurrency;

import java.util.concurrent.*;
import java.util.*;

class KrzyJob implements Callable<Integer> {
    private String sentence ;
    KrzyJob( String sentence ) {
        this.sentence = sentence ;
    }
    public Integer call() {
        try { 
            for( int i=0; i<5;i++) {
                TimeUnit.MILLISECONDS.sleep(100); 
                System.out.println(Thread.currentThread() + " " + this); 
            } 
            if ( this.sentence.length() > 90 ) throw new RuntimeException() ;
        } catch(InterruptedException e) { 
            System.out.println("---> thread is interrupted"); 
        } catch( Exception e ) {
            System.out.println("-----> cought all other exceptions"); 
        }
        return Integer.valueOf( this.sentence.length() ) ;
    }
}


public class CallableDemo  {
    private static final int MAX_SENTENCES = 10 ;
    private static final int MAX_SENTENCE_LENGTH = 10 ;

    public static void main(String[] args) { 
        // create sentences
        List<String> sentences = new ArrayList<String>() ;
        Random random = new Random();
        for( int i=0; i<MAX_SENTENCES; i++) {
            sentences.add( createRandomSentence( random.nextInt( MAX_SENTENCE_LENGTH ) ) ) ;
        }

        // execute
        ExecutorService exec = Executors.newCachedThreadPool(); 
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>(); 
        for( String sentence : sentences ) {
            KrzyJob job = new KrzyJob( sentence ) ;
            results.add(exec.submit( job )); 
        }

        // interrump 1 thread
        results.get(1).cancel( true ) ;

        // get results
        int sum = 0 ;
        for(Future<Integer> fs : results) {
            try { 

                // get() blocks until completion:
                // check if thread was canceled
                if ( ! fs.isCancelled() ) {
                    int value = fs.get() ; 
                    System.out.println(value); 
                    sum += value;
                }
            } catch(InterruptedException e) { 
                System.out.println( " InterruptedException 1 "+e); 
                return; 
            } catch(ExecutionException e) { 
                System.out.println( "ExecutionException 1 "+e); 
            } finally { 
                exec.shutdown(); 
            } 
        }
        System.out.println("Total:"+sum); 
    } 

    private static String createRandomSentence( int length ) {
        StringBuffer result = new StringBuffer();

        for( int i=0; i<length; i++) {
            result.append( createRandomString() ) ;
            result.append( " " ) ;
        }
   //     System.out.println( result.toString()  ) ;
        return result.toString() ;
    }

    private static String createRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String result = random.ints( leftLimit, rightLimit+1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString() ;

        return result ;
    }

}
