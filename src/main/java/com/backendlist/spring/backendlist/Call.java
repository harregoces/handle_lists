package com.backendlist.spring.backendlist;

public class Call {

	private String name       =  null;
    private boolean cancelled = false;
    private boolean inQueue   = false;
    private Agent agent    = null;
    private boolean isAttended = false;
    
    
    /**
	* get if the call has been attended
	* 
	* @return isAttended       Boolean
	*/
	public boolean isAttended() {
		return isAttended;
	}

	/**
	* Set if a call has been attended
	* 
	* @param isAttended       Boolean
	*/
	public void setAttended(boolean isAttended) {
		this.isAttended = isAttended;
	}


	/**
	* Set agent
	* 
	* @param agent    the agent who will attend the call. 
	*/
	public void setAgent(Agent agent) {
		this.agent = agent;
	}


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
	  * @param name     name of the call
	  * @param agent    the agent who will attend the call.
	  */
	 public Call(String name, Agent agent) {
	     this.name    = name;
	     this.agent = agent;
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
	* Get the call agent
	* 
	* @return   Agent of the call
	*/
	public Agent getAgent() {
		return this.agent;
	}

     
}
