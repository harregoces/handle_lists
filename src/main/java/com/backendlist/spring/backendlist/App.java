package com.backendlist.spring.backendlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Queue<Call> callList = new LinkedList<>();
	
	
    public static void main( String[] args ) throws InterruptedException
    {
    	
    	while (!callList.isEmpty()) {
            System.out.println(callList.remove());
            Thread.sleep(1000);
        }
    	 	
    	
        System.out.println( "Hello World!" );
    }
    
    void enqueueCall(Call ch) {
    	callList.add(ch);
    }
    
    Call dequeueCall() {
        return callList.remove();
    }
    
}
