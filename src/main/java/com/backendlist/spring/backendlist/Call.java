package com.backendlist.spring.backendlist;

public class Call {

	private String name       =  null;
    private boolean cancelled = false;
    private boolean inQueue   = false;
    private Object context    = null;
    private Object userInfo   = null;
    
    /**
     * Create a new call
     * 
     * @param name       name of the call
     */
     public Call(String name) {
         this.name = name;
     }
     
     
     /**
      * Create a new call
      * 
      * @param name       name of the call
      * @param context    context of the call
      */
     public Call(String name, Object context) {
         this.name    = name;
         this.context = context;
     }
     
     /**
      * Create a new call
      * 
      * @param name       name of the call
      * @param context    context of the call
      * @param userInfo   agent for the call
      */
     public Call(String name, Object context, Object userInfo) {
         this.name     = name;
         this.context  = context;
         this.userInfo = userInfo;
     }
     
     
     
     /**
      * Cancel the call
      */
      public void cancel() {
          this.cancelled = true;
      }

      /**
       * Flag the call as queued
       */
       public void queueCall() {
           this.inQueue = true;;
       }
      
     /**
      * Check, whether the call has been cancelled
      * 
      * @return       true, if the call has been cancelled, false otherwise
      */
      public boolean isCancelled() {
          return this.cancelled;
      }

      /**
       * Check, whether the call already is in a queue
       * 
       * @return       true, if the call is queued, false otherwise
       */
       public boolean isQueued() {
           return this.inQueue;
       }
      
     /**
      * Get the name of the call
      * 
      * @return   name of the call
      */
      public String getName() {
          return this.name;
      }

     /**
      * Get the call context
      * 
      * @return   Context of the call
      */
      public Object getContext() {
          return this.context;
      }

     /**
      * Get the (optional) agent
      * 
      * @return   any agent that has been passed to the constructor
      */
      public Object getUserInfo() {
          return this.userInfo;
      }
      
      
     
}
