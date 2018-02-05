package com.backendlist.spring.backendlist;

import junit.framework.TestCase;

public class CallTest extends TestCase {

	private Dispatcher dispatcher;
	
	public void setUp() throws Exception {
		super.setUp();
		this.dispatcher = Dispatcher.getInstance();
	}
	
	/*
	* Test method for 'Dispatcher.getCallQueue().addCall(Call)'
	*/
	public void testAddListener() throws Exception {
		Call c1 = new Call("Call 1");
		this.dispatcher.getCallQueue().addCall(c1);
		
		Call c2 = new Call("Call 2");
		this.dispatcher.getCallQueue().addCall(c2);
		
		Call c3 = new Call("Call 3");
		this.dispatcher.getCallQueue().addCall(c3);
		
		
		TestCase.assertEquals(3, this.dispatcher.getCallQueue().getQueuedCalls().size());
		
	}
	
}
