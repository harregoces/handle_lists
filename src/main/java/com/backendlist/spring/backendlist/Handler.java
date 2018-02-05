package com.backendlist.spring.backendlist;

public interface Handler {
	public boolean handleCall(Call c);
	public boolean isBusy();
	public Agent.ROLE getRole_id();
	public int getAmountOfCalls();
}
