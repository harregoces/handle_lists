package com.backendlist.spring.backendlist;

public class App 
{
	
    public static void main( String[] args ) throws Exception {
    	
    	CallListener echo = new DebugHandler();
    	CallListener echo1 = new EchoHandler();
    	CallDispatcher disp = CallDispatcher.getInstance();
        
    	disp.addListener("call 1", echo);
    	disp.addListener("call 1", echo1);
        
    	Call c = (Call) disp.triggerCall("call 1", true);
    	Call c2 = (Call) disp.triggerCall("call 1", true);
    	
    }
    
    
}
