package com.backendlist.spring.backendlist;

import java.util.ArrayList;
import java.util.Iterator;

public class CallListenerCollection {

	/**
    * All call listeners
    */
    private ArrayList<CallListenerContainer> listeners = new ArrayList<CallListenerContainer>();
	    

    /**
     * Add a new call listener to the collection
     * 
     * @param listener       Listener to add
     * @return               amount of call listeners in this collection
     */
     public int addListener(CallListener listener, boolean autoRemove) {

         CallListenerContainer container = new CallListenerContainer(listener);
         container.enableAutoRemove(autoRemove);
         
         this.listeners.add(container);
         return this.listeners.size();
     }
     
     
     
     /**
      * Propagate an call to all listeners in this collection
      * 
      * @param e      event to propagate
      * @return       event
      */
      public Call propagate(Call c) throws Exception {
          ArrayList<CallListener> remove = new ArrayList<CallListener>();
          for (int i = 0; i < this.listeners.size(); i++) {
              CallListenerContainer container = (CallListenerContainer)this.listeners.get(i);
              container.getListener().handleCall(c);
              
              // remove the listener
              if (container.isAutoRemoveEnabled()) {
                  remove.add(container.getListener());
              }
              if (c.isCancelled()) {
                  break;
              }
          }
          // remove the listeners that have been set to autoRemove
          for (Iterator<CallListener> iter = remove.iterator(); iter.hasNext();) {
              CallListener listener = (CallListener) iter.next();
              this.removeListener(listener);
          }
          return c;
      }
      
      
      /**
       * Remove a listener from the list
       * 
       * @param index  index of the call listener   
       * @return
       */
       public CallListenerContainer removeListener(int index) {
           return (CallListenerContainer)this.listeners.remove(index);
       }
       
       
       /**
        * Remove a listener of a specific class from the list
        * 
        * @param className
        * @return
        */
       public CallListenerContainer removeListener(String className) {
           for (Iterator<CallListenerContainer> iter = this.listeners.iterator(); iter.hasNext();) {
               CallListenerContainer container = (CallListenerContainer) iter.next();
               if (container.getListener().getClass().getName().equals(className)) {
                   this.listeners.remove(container);
                   return container;
               }
           }
           return null;
       }
       
       
       
       /**
        * Remove a listener from the list
        * 
        * If a listener has been added more the once, only the
        * first listener is removed
        * 
        * @param listener      listener to remove   
        * @return
        */
        public CallListenerContainer removeListener(CallListener listener) {
            for (Iterator<CallListenerContainer> iter = this.listeners.iterator(); iter.hasNext();) {
               CallListenerContainer container = (CallListenerContainer) iter.next();
               if (container.getListener().equals(listener)) {
                   this.listeners.remove(container);
                   return container;
               }
            }
            return null;
        }
        
        
        
        
        /**
         * Remove all listeners from this collection
         */
        public void removeAllListeners() {
       	 this.listeners.clear();
        }
        
        
        /**
         * Get an iterator to iterate over the event listeners in this
         * Collection
         * 
         * @return
         */
        public Iterator<CallListenerContainer> iterator() {
            return this.listeners.iterator();
        }
        
     
     
}
