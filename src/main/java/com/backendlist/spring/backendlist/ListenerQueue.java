package com.backendlist.spring.backendlist;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ListenerComparator implements Comparator<Handler>
{
	
   public int compare(Handler h1, Handler h2)
   {
       int res = h1.getRole_id().compareTo(h2.getRole_id());
       if(res == 0) {
    	   return h1.getAmountOfCalls() - h2.getAmountOfCalls();
       }
       return res;
   }
   
}

public class ListenerQueue {

	/**
	* All queued Listener
	*/
	private List<Handler> listeners = new LinkedList<Handler>();

	/**
	 * Add a new call listener to the collection
	 * 
	 * @param listener       Listener to add
	 * @return               amount of call listeners in this collection
	 */
	 public int addListener(Handler listener) {
		 this.listeners.add(listener);
	     return this.listeners.size();
	 }
   
	/**
	* Propagate an call to all listeners in this collection
	* 
	* @param e      event to propagate
	* @return       event
	*/
	public boolean propagate(Call c) throws Exception {
		  
		for (Iterator<Handler> iter = this.orderListenerQueue().iterator(); iter.hasNext(); ) {
	        Handler agent = (Handler) iter.next();
	        if ( agent.isBusy() == false ) {
				if(agent.handleCall(c))
					return true;
			}
	    }
		
		return false;
	      
	}
	
	
	public List<Handler> orderListenerQueue () {
		Collections.sort(this.listeners,new ListenerComparator());
		return this.listeners;
	}
	
}
