package com.backendlist.spring.backendlist;

import java.util.Queue;

/**
 * Dispatcher for the calls
 */
public class Dispatcher {

	/**
	* Stores listeners, that handle all calls
	*/
	private ListenerQueue listeners = new ListenerQueue();
     
    
	/**
	* Queue that stores the triggered events so they
	* still can be propagated to event listeners that are registered
	* at a later time
	*/
	private CallQueue calls = new CallQueue();

	
	public CallQueue getCallQueue () {
		return calls;
	}
	
	/**
     * Constructor is private.
     *
     * Use getInstance() or getDetachedInstance() instead.
     */
    private Dispatcher() {
    }
     
	/**
	* Get an instance and do not remember it. 
	* 
	* @return
	*/
	synchronized public static Dispatcher getInstance() {
		return new Dispatcher();
	}
      
	
	/**
	* Add an call listener object
	* 
	* @param callName      name of the call to listen on
	* @param listener       instance of the call listener
	* @param autoRemove     whether to remove the listener after the first call it has handled
	* @throws Exception 
	*/
	 public void addListener(Handler listener) {
		 this.listeners.addListener(listener);
	}
       
               
	/**
	* Propagate a call to all listeners that have been registered
	* 
	* @param c      The call
	* @param queue  Whether you want the call to be queued or not
	* @return       The modified call
	*/
	public void dispatchCall() throws Exception {
	
		Queue<Call> calls = this.getCallQueue().getQueuedCalls();
		System.out.println("Call size : " + calls.size());
		while(!calls.isEmpty() ) {
			Call c = (Call) calls.element();
			if(this.listeners.propagate(c) ) {
				
				//remove the call from queue
				calls.remove();
				
				//move 1 call from callbacklist to call list
				if(!this.getCallQueue().getQueuedCallBacks().isEmpty()) {
					this.getCallQueue().addCall(this.getCallQueue().getQueuedCallBacks().remove());
				}
			} else {
				//there is nor agent available
				break;
			}
		}
		
	}

	public void reset() {
		//clear call queue
		this.calls.clearQueue();
	}
	               
              
}
