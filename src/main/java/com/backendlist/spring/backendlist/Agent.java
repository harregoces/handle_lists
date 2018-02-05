package com.backendlist.spring.backendlist;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Agent implements Handler {

	private int role_id;
	private String name;
	private boolean isBusy = false;
	
	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	private Dispatcher disp = null;
	
	public Agent(String name, int role_id) {
		System.out.println("Agent : " + name);
        this.name    = name;
        this.role_id = role_id;
        this.disp    = Dispatcher.getInstance();
    }

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dispatcher getDisp() {
		return disp;
	}

	public void setDisp(Dispatcher disp) {
		this.disp = disp;
	}

    
    public void observe(Agent a) {
        System.out.println("observe called and customer " + a.getName() + " passed.");
    }

	public boolean handleCall(Call c) {
		
		//check if agent is handle a call
		if(!this.isBusy) {
			this.isBusy = true;
			c.setAgent(this);
			c.setAttended(true);
			
			//mock attend the call 
			this.attendCall(c);
			System.out.println("User " + this.getName() + " is attendin the call : " + c.getName() );
			return true;
		}
		
		return false;
	}
	
	private void attendCall(final Call c) {
		
		Thread t1 = new Thread( new Runnable() {

			public void run() {
				
				try {
					
					int max = 10;
					int min = 5;
					Random randomNumb = new Random();
					int r = min + randomNumb.nextInt(max);
					
					//time to attend the call
					Thread.sleep( TimeUnit.SECONDS.toMillis(r) );
					
					isBusy = false;
					c.cancel();
					//System.out.println("Call was attended, time : " + r);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		t1.start();
		
	}
	
}
