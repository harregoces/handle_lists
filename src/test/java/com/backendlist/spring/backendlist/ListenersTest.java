package com.backendlist.spring.backendlist;

import junit.framework.TestCase;

public class ListenersTest extends TestCase {

	private Dispatcher dispatcher;
	
	public void setUp() throws Exception {
		super.setUp();
		this.dispatcher = Dispatcher.getInstance();
	}
	
	/*
	* Test method for 'Dispatcher.addListener(Listener)'
	*/
	public void testAddListener() throws Exception {
		Agent agent1 = new Agent("Test1",Agent.ROLE.OPERATOR);
		this.dispatcher.addListener(agent1);
		
		Agent agent2 = new Agent("Test2",Agent.ROLE.OPERATOR);
		this.dispatcher.addListener(agent2);
		
		Agent agent3 = new Agent("Test3",Agent.ROLE.OPERATOR);
		this.dispatcher.addListener(agent3);
		
		TestCase.assertEquals(3, this.dispatcher.getListenerQueue().getListeners().size());
		
	}
	
	
}
