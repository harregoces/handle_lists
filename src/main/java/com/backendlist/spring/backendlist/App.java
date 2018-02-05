package com.backendlist.spring.backendlist;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App 
{
	/*
	 * Dispatcher var
	 */
	public static Dispatcher disp = Dispatcher.getInstance();
	
    public static void main( String[] args ) throws Exception {
    	
    	CreateAgents();
    	createCalls();
    	handleCalls();
    	
    }

    
	/**
	 * Create 100 calls in background in a range from 1 to 5 second.
	 * The goal is to simulate real entrance calls to the call center.
	 */
	public static void createCalls() {
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				
				int i = 0;
				
				while(i<100) {
					
					try {
						
						i++;
	    				System.out.println("CallCreateer : " + i);
			
    					Call c = new Call("CallNumber : " + i);
						disp.getCallQueue().addCall(c);
						
						int max = 5;
						int min = 1;
						Random randomNumb = new Random();
						int r = randomNumb.nextInt(max-min) + min;
						
						Thread.sleep( TimeUnit.SECONDS.toMillis(r) );
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		});
	
		t1.start();
	}
    
	
	/**
	 * Create 7 agents, 5 with operator role, 1 with supervisor role and 1 with director role
	 */
    public static void CreateAgents() throws Exception {
    	
    	//create agent
    	for(int i = 0; i < 5; i ++) {

			Handler agent = new Agent("Agent Name " + i, Agent.ROLE.OPERATOR);
			disp.addListener(agent);
			
		}
    	
    	//create 1 supervisor
    	Handler supervisor = new Agent("Supervisor Name ", Agent.ROLE.SUPERVISOR);
		disp.addListener(supervisor);
		
		//create 1 director
    	Handler director = new Agent("Director Name ", Agent.ROLE.DIRECTOR);
		disp.addListener(director);
		
    }
    
    /**
	 * Call Dispatcher.dispatchCall every one second in background to handle every entrance call.
	 */
    public static void handleCalls() {
    	
    	Thread t1 = new Thread(new Runnable() {
    		public void run() {
    			
    			while(true) {
    				try {
						disp.dispatchCall();
						Thread.sleep( TimeUnit.SECONDS.toMillis(1) );
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
    				
    			}
    		}
    	});
    	
    	t1.start();
    	
    }
    
}

