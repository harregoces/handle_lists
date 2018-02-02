package com.backendlist.spring.backendlist;

public class Agent {

	private int role_id;
	private String name;
	
	private CallDispatcher disp = null;
	
	public Agent(String name, int role_id) {
        this.name    = name;
        this.role_id = role_id;
        this.disp    = CallDispatcher.getInstance();
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

	public CallDispatcher getDisp() {
		return disp;
	}

	public void setDisp(CallDispatcher disp) {
		this.disp = disp;
	}
	
	public boolean login() throws Exception {
        Call c = (Call)this.disp.triggerCall("onLogin", true, this);
        if (c.isCancelled()) {
            System.out.println("User " + this.getName() + " is not allowed to login.");
            return false;
        }
        return true;
    }
    
    public void observe(Agent a) {
        System.out.println("observe called and customer " + a.getName() + " passed.");
    }
    
    
}
