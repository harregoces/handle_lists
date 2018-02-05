package com.backendlist.spring.backendlist;

import java.util.LinkedList;
import java.util.Queue;

public class CallQueue {

	private int limit = 10;
	
	/**
    * All queued calls
    */
    Queue<Call> calls = new LinkedList<Call>();
	
    /**
     * All queued calls
     */
     Queue<Call> callBacks = new LinkedList<Call>();
     
     
    
	/**
	 * Add a new call to the queue.
	 * 
	 * The inQueue property of the call will automatically
	 * be set to 'true'.
	 * 
	 * @param c      Call that will be added
	 */
	 public void addCall(Call c) {
		 
		 if(this.calls.size() < limit) {
			 c.queueCall();
	         this.calls.add(c);
		 } else {
			 
			 this.callBacks.add(c);
			 //more than ten, process queue for callBack
			 //System.out.println("Call to the callback list");
		 }
		 
	     
	 }
	    
     
	 /**
	  * Get all queued Calls
	  * 
	  * @return   ArrayList with all Calls
	  */
	  public Queue<Call> getQueuedCalls() {
	      return this.calls;
	  }
      
      /**
       * Get all queued Calls
       * 
       * @return   Queue with all Calls
       */
       public Queue<Call> getQueuedCallBacks() {
           return this.callBacks;
       }
       
      

       /**
        * Clear the call queue
        */
       public void clearQueue() {
       	this.calls.clear();
       }
       
       
      
}
