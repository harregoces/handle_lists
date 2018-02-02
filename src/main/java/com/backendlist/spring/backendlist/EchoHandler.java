package com.backendlist.spring.backendlist;

public class EchoHandler implements CallListener {

    public void handleCall(Call c) throws Exception {
        Agent a = (Agent)c.getContext();
        //System.out.println("Event handled: " + c.getName() + "("+a.getName()+")");
        System.out.println("Event handled: " + c.getName() );
    }

}