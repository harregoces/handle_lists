package com.backendlist.spring.backendlist;

import java.util.ArrayList;
import java.util.Iterator;

public class CallQueue {

	/**
    * All queued calls
    */
    ArrayList<Call> calls = new ArrayList<Call>();
	    
    /**
     * Add a new call to the queue.
     * 
     * The inQueue property of the call will automatically
     * be set to 'true'.
     * 
     * @param c      Call that will be added
     */
     public void addCall(Call c) {
         c.queueCall();
         this.calls.add(c);
     }
	    
     
     /**
      * Get all queued Calls
      * 
      * @return   ArrayList with all Calls
      */
      public ArrayList<Call> getQueuedCalls() {
          return this.calls;
      }
      
      
      
      /**
       * Get queued calls of a specific call name
       * 
       * @param    callName       name of the call
       * @return   queued calls
       */
       public ArrayList<Call> getQueuedCalls(String callName) {
           ArrayList<Call> qCalls = new ArrayList<Call>();
           
           for (Iterator<Call> iter = this.calls.iterator(); iter.hasNext();) {
               Call c = (Call)iter.next();
               if (c.getName().equals(callName)) {
                   qCalls.add(c);
               }
           }
           return qCalls;
       }

       /**
        * Clear the call queue
        */
       public void clearQueue() {
       	this.calls.clear();
       }
       
       
      
}
