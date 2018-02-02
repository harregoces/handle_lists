package com.backendlist.spring.backendlist;

public class DebugHandler implements CallListener{

    private String name = "No-Name";

    public DebugHandler() {
    }
    
    public DebugHandler(String name) {
        this.name = name;
    }
    
    /**
     * Handle the event
     */
    public void handleCall(Call c) throws Exception {
        System.out.println("Event caught by '" + this.name + "'");
        System.out.println("Event-name    : " + c.getName());
        System.out.println("Event-context : " + ((Agent) (c.getContext())).getName());
        System.out.println("in queue      : " + c.isQueued());
        System.out.println();
    }
    
   /**
    * Get the name of the handler
    * 
    * @return
    */
    public String getName() {
        return this.name;
    }

    
}