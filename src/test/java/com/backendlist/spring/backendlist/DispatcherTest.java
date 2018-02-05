package com.backendlist.spring.backendlist;

import junit.framework.TestCase;

public class DispatcherTest extends TestCase {

private Dispatcher dispatcher;
	
	public void setUp() throws Exception {
		super.setUp();
		this.dispatcher = Dispatcher.getInstance();
	}
	
	/*
	* Test method for 'Dispatcher.addListener(Listener)'
	*/
	public void testDispatchCall() throws Exception {
		
		//add 3 agent call
		
		Agent agent1 = new Agent("Test1",Agent.ROLE.OPERATOR);
		this.dispatcher.addListener(agent1);
		
		Agent agent2 = new Agent("Test2",Agent.ROLE.OPERATOR);
		this.dispatcher.addListener(agent2);
		
		Agent agent3 = new Agent("Test3",Agent.ROLE.OPERATOR);
		this.dispatcher.addListener(agent3);
		
		//add 3 call
		Call c1 = new Call("Call 1");
		this.dispatcher.getCallQueue().addCall(c1);
		
		Call c2 = new Call("Call 2");
		this.dispatcher.getCallQueue().addCall(c2);
		
		Call c3 = new Call("Call 3");
		this.dispatcher.getCallQueue().addCall(c3);
		
		//dispatch call
		this.dispatcher.dispatchCall();
		
		//Queue call size should be 0 because all calls was attended
		TestCase.assertEquals(0, this.dispatcher.getCallQueue().getQueuedCalls().size());
	}
}
