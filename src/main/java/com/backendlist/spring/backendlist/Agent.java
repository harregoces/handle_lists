package com.backendlist.spring.backendlist;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Agent implements Handler {

	public enum ROLE { 
		OPERATOR(1), 
		SUPERVISOR(2), 
		DIRECTOR(3); 
		
		private final int level;

		ROLE(int l) {
			level = l;
		}

		int getLevel(){ return level; }  

		public static Comparator<ROLE> RoleComparator = new Comparator<ROLE>() {
			public int compare(ROLE r1, ROLE d2) {
				return r1.getLevel() - d2.getLevel();
			}
		};
		  
	}
	private ROLE role_id;
	private String name;
	private boolean isBusy = false;
	private int amountOfCalls = 0;
	
	
	
	public int getAmountOfCalls() {
		return amountOfCalls;
	}

	public void setAmountOfCalls(int amountOfCalls) {
		this.amountOfCalls = amountOfCalls;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	private Dispatcher disp = null;
	
	public Agent(String name, ROLE role_id) {
		System.out.println("Agent : " + name);
        this.name    = name;
        this.role_id = role_id;
        this.disp    = Dispatcher.getInstance();
    }

	public ROLE getRole_id() {
		return role_id;
	}

	public void setRole_id(ROLE role_id) {
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
			System.out.println("User " + this.getName() + " is attendin the call : " + c.getName() + " amount of calls today : " + this.amountOfCalls );
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
					int r = randomNumb.nextInt(max-min) + min;
					
					//time to attend the call
					Thread.sleep( TimeUnit.SECONDS.toMillis(r) );
					
					//the call was attended
					isBusy = false;
					amountOfCalls++;
					c.cancel();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		t1.start();
		
	}
	
}
