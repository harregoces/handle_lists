package com.backendlist.spring.backendlist;

public class App 
{
	
    public static void main( String[] args ) throws Exception {
    	
    	Agent cus1 = new Agent("Stephan Schmidt" , 1);
        cus1.login();
        
        Agent cus2 = new Agent("Andreas Foerch",2);
        cus2.login();
        
        Agent cus3 = new Agent("Andreas Foerch",2);
        cus3.login();
        
        
        CallListener echo = new DebugHandler("MyList");
        CallDispatcher disp = CallDispatcher.getInstance();
        disp.addListener("onLogin", echo);
        
        
        
    }
    
    
}
