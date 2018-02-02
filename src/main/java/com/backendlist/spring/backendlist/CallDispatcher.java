package com.backendlist.spring.backendlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Dispatcher for the calls
 */
public class CallDispatcher {

	/**
    * Stores all listeners
    */
    private HashMap<String,CallListenerCollection> listeners = new HashMap<String,CallListenerCollection>();

    /**
     * Stores global listeners, that handle all events
     */
     private CallListenerCollection globalListeners = new CallListenerCollection();
     
     
	/**
	* Stores all instances that previously have been created
	*/
	private static HashMap<String,CallDispatcher> instances = new HashMap<String,CallDispatcher>();
	
	/**
	* Queue that stores the triggered events so they
	* still can be propagated to event listeners that are registered
	* at a later time
	*/
	private CallQueue queue = new CallQueue();
	    
	
	/**
     * Constructor is private.
     *
     * Use getInstance() or getDetachedInstance() instead.
     */
    private CallDispatcher() {
    }
    
    
    /**
     * Get the default dispatcher
     * 
     * @return   EventDispatcher object
     */
     synchronized public static CallDispatcher getInstance() {
         return CallDispatcher.getInstance("__default");
     }
     
     /**
      * Create a new dispatcher or return an existing one,
      * if it has been created
      * 
      * @param name   unique name of the dispatcher instance
      * @return       CallDispatcher object
      */
      synchronized public static CallDispatcher getInstance(String name) {
          if (!CallDispatcher.instances.containsKey(name)) {
              CallDispatcher.instances.put(name, new CallDispatcher());
          }
          return (CallDispatcher)CallDispatcher.instances.get(name);
      }
      
      
      /**
       * Get an instance and do not remember it. 
       * 
       * @return
       */
      synchronized public static CallDispatcher getDetachedInstance() {
          return new CallDispatcher();
      }
     
      
      /**
       * Detach a dispatcher
       * 
       * When detaching a dispatcher getInstance() will return a fresh
       * dispatcher instead of the old one.
       * 
       * @param name
       * @return
       */
      synchronized public static boolean detachDispatcher(String name) {
          if (!CallDispatcher.instances.containsKey(name)) {
              return false;
          }
      	CallDispatcher.instances.remove(name);
      	return true;
      }
      
      
      
      /**
       * Check, whether a specified CallDispatcher instance already has been created
       * 
       * @param name
       * @return
       */
      synchronized public static boolean dispatcherExists(String name) {
      	return CallDispatcher.instances.containsKey(name);
      }
      
      
      /**
       * Add a call listener object
       * 
       * @param callName      name of the call to listen on
       * @param listener       instance of the call listener
       * @throws Exception 
       */
       public void addListener(String callName, CallListener listener) throws Exception {
           this.addListener(callName, listener, false);
       }
       
       
       
       /**
        * Add a call listener object
        * 
        * @param callName      name of the call to listen on
        * @param listener       instance of the call listener
        * @param autoRemove     whether to remove the listener after the first call it has handled
        * @throws Exception 
        */
        public void addListener(String callName, CallListener listener, boolean autoRemove) throws Exception {
            if (!this.listeners.containsKey(callName)) {
                this.listeners.put(callName, new CallListenerCollection());
            }
            CallListenerCollection col = (CallListenerCollection)this.listeners.get(callName);
            col.addListener(listener, autoRemove);
            
            // check the event queue
            ArrayList calls = this.queue.getQueuedCalls(callName);
            
            for (Iterator iter = calls.iterator(); iter.hasNext();) {
                Call c = (Call)iter.next();
                this.dispatchCall(c, false);
            }
        }
       
        
        
        /**
         * Remove an call listener
         * 
         * @param callName      name of the call
         * @param className      the class name of the listener
         * @return
         */
         public CallListener removeCallListener(String callName, String className) {
             if (!this.listeners.containsKey(callName)) {
                 return null;
             }
             CallListenerCollection collection = (CallListenerCollection)this.listeners.get(callName);
             CallListenerContainer container = (CallListenerContainer)collection.removeListener(className);
             if (container != null) {
                 return container.getListener();
             }
             return null;
         }
         
         
         
         /**
          * Remove an call listener
          * 
          * @param callName      name of the call
          * @param listener       the call listener object
          * @return
          */
          public CallListener removeCallListener(String callName, CallListener listener) {
              if (!this.listeners.containsKey(callName)) {
                  return null;
              }
              CallListenerCollection collection = (CallListenerCollection)this.listeners.get(callName);
              CallListenerContainer container = (CallListenerContainer)collection.removeListener(listener);
              if (container != null) {
                  return container.getListener();
              }
              return null;
          }
     
          
          
          
          /**
           * Add an call listener object
           * 
           * @param callName      name of the call to listen on
           * @param listener       instance of the call listener
           * @param autoRemove     whether to remove the listener after the first call it has handled
           * @throws Exception 
           */
           public void addGlobalListener(CallListener listener, boolean autoRemove) throws Exception {
               this.globalListeners.addListener(listener, autoRemove);
               
               // check the event queue
               ArrayList events = this.queue.getQueuedCalls();
               
               for (Iterator iter = events.iterator(); iter.hasNext();) {
                   Call c = (Call)iter.next();
                   this.dispatchCall(c, false);
               }
           }
 
           
           /**
            * Remove an call listener, that has been globally added
            * 
            * @param listener       the call listener object
            * @return               
            */
             public CallListener removeGlobalCallListener(CallListener listener) {
                 CallListenerContainer container = (CallListenerContainer)this.globalListeners.removeListener(listener);
                 if (container != null) {
                     return container.getListener();
                 }
                 return null;
             }
             
           
             
         /**
          * Remove an call listener, that has been globally added
          * 
          * @param className       the classname of the call listener
          * @return
          */
           public CallListener removeGlobalCallListener(String className) {
               CallListenerContainer container = (CallListenerContainer)this.globalListeners.removeListener(className);
               if (container != null) {
                   return container.getListener();
               }
               return null;
           }
               
               
           /**
            * Trigger a call, if you already created an call object
            * 
            * The Call object will not be queued.
            * 
            * @param c  Call that will be triggered
            * @return   The call object
            * @throws Exception 
            */
            public Call triggerCall(Call c) throws Exception {
                return this.dispatchCall(c, false);
            }
            
            
               
            /**
             * Trigger a call, if you already created an call object
             * 
             * @param c      Call that will be triggered
             * @param queue  Whether to queue the call
             * @return       The call object
             * @throws Exception 
             */
             public Call triggerCall(Call c, boolean queue) throws Exception {
                 return this.dispatchCall(c, queue);
             }
             
             
             
             /**
              * Trigger a call that has no context information
              * 
              * The Call will not be queued.
              * 
              * @param name   name of the call
              * @return       The Call object
              * @throws Exception 
              */
              public Call triggerCall(String name) throws Exception {
                  Call c = new Call(name);
                  return this.dispatchCall(c, false);
              }
              
              
              
              
              /**
               * Trigger a call that has no context information
               * 
               * @param name   name of the call
               * @param queue  Whether to queue the call
               * @return       The Call object
               * @throws Exception 
               */
               public Call triggerCall(String name, boolean queue) throws Exception {
                   Call c = new Call(name);
                   return this.dispatchCall(c, queue);
               }
               
               
               /**
                * Trigger a call with context information
                * 
                * @param name      Name of the call
                * @param queue     Whether to queue the call
                * @param context   Context of the call
                * @return          The Call object
                * @throws Exception 
                */
               public Call triggerCall(String name, boolean queue, Object context) throws Exception {
                   Call c = new Call(name, context);
                   return this.dispatchCall(c, queue);
               }
               
               
               /**
                * Trigger a call with context and user information
                * 
                * @param name      Name of the call
                * @param queue     Whether to queue the call
                * @param context   Context of the call
                * @param userInfo  Any additional information for the call 
                * @return          The Call object
                * @throws Exception 
                */
               public Call triggerCall(String name, boolean queue, Object context, Object userInfo) throws Exception {
                   Call c = new Call(name, context, userInfo);
                   return this.dispatchCall(c, queue);
               }
               
               
               
               /**
                * Propagate a call to all listeners that have been registered
                * 
                * @param c      The call
                * @param queue  Whether you want the call to be queued or not
                * @return       The modified call
                */
                private Call dispatchCall(Call c, boolean queue) throws Exception {
                    if (this.listeners.containsKey(c.getName())) {
                        CallListenerCollection col = (CallListenerCollection)this.listeners.get(c.getName());
                        col.propagate(c);
                    }

                    if (c.isCancelled()) {
                        return c;
                    }
                    
                    this.globalListeners.propagate(c);

                    if (c.isCancelled() || queue == false) {
                        return c;
                    }       
                    // add this event to the queue
                    this.queue.addCall(c);
                    return c;
                }
               
                
                
                
                /**
                 * Get the names of all calls for which any listeners
                 * have been added.
                 * 
                 * @return	Set containing all call names
                 */
                public String[] getRegisteredCallNames() {
                	String[] a = {};
                	String[] names = this.listeners.keySet().toArray(a);
                	return names;
                }
                
                
                
                
                
                /**
                 * Get all call listeners of the spefic call
                 * 
                 * @param callName
                 * @return
                 */
                public CallListenerCollection getCallListeners(String callName) {
                    if (this.listeners.containsKey(callName)) {
                        return (CallListenerCollection)this.listeners.get(callName);
                    }
                    return new CallListenerCollection();
                }
                
                
                
                /**
                 * Get the global call listeners
                 * 
                 * @return
                 */
                public CallListenerCollection getGlobalCallListeners() {
                    return this.globalListeners;
                }
                
                
                
                /**
                 * Remove all call listeners from this dispatcher and clear the queue
                 */
                public void reset() {
                	for (Iterator iter = this.listeners.values().iterator(); iter.hasNext();) {
            			CallListenerCollection listeners = (CallListenerCollection) iter.next();
            			listeners.removeAllListeners();
            		}
                	this.globalListeners.removeAllListeners();
                	this.queue.clearQueue();
                }
                
                
                
                
                
                
              
}
