package com.backendlist.spring.backendlist;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class ListenerComparator implements Comparator<String>
{
   public int compare(Handler h1, Handler h2)
   {
       if (h1.isBusy() == true ) return -1;
       else return 1;
   }

   public int compare(String str1, String str2) {
	   if (str1.length() < str2.length()) return -1;
	   else                               return 1;
   }
   
}

public class ListenerQueue {

	/**
	* All queued Listener
	*/
	private Queue<Handler> listeners = new LinkedList<Handler>();

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
		  
		for (Iterator<Handler> iter = this.listeners.iterator(); iter.hasNext(); ) {
	        Handler agent = (Handler) iter.next();
	        if ( agent.isBusy() == false ) {
				if(agent.handleCall(c))
					return true;
			}
	    }
		
		return false;
	      
	}
	
	public Queue<Handler> orderListenerQueue () {
		return this.listeners;
	}
	
}
