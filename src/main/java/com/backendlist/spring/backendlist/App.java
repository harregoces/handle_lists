package com.backendlist.spring.backendlist;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App 
{
	public static Dispatcher disp = Dispatcher.getInstance();
	
    public static void main( String[] args ) throws Exception {
    	
    	CreateAgents();
    	createCalls();
    	handleCalls();
    }

    public static void createCalls() {
    	
    	Thread t1 = new Thread(new Runnable() {
    		
    		public void run() {
    			
    			int i = 0;
    			
    			while(true) {
    				
    				try {
    					
    					i++;
        				//System.out.println("CallCreateer : " + i);
        				
        				Call c = new Call("CallNumber : " + i);
    					disp.getCallQueue().addCall(c);
    					
    					int max = 10;
    					int min = 1;
    					Random randomNumb = new Random();
    					int r = min + randomNumb.nextInt(max);
    					
    					Thread.sleep( TimeUnit.SECONDS.toMillis(1) );
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    				
    			}
    			
    		}
    		
    	});
    
    	t1.start();
    }
    
    public static void CreateAgents() throws Exception {
    	
    	//create agent
    	for(int i = 0; i < 10; i ++) {

			Handler agent = new Agent("Agent Name " + i, 1);
			disp.addListener(agent);
			
		}
    	
    	//create 1 supervisor
    	Handler supervisor = new Agent("Supervisor Name ", 2);
		disp.addListener(supervisor);
		
		//create 1 director
    	Handler director = new Agent("Director Name ", 3);
		disp.addListener(director);
		
    }
    
    public static void handleCalls() {
    	
    	Thread t1 = new Thread(new Runnable() {
    		public void run() {
    			
    			while(true) {
    				
    				try {
						disp.dispatchCall();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
    			}
    			
    		}
    	});
    	
    	t1.start();
    	
    }
    
}

