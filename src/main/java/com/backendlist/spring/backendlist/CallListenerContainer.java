package com.backendlist.spring.backendlist;

public class CallListenerContainer {

	/**
     * The actual call listener
     */
    private CallListener listener = null;
    
    /**
     * Whether to use autoRemove
     */
    private boolean autoRemove = false;
    
    /**
     * Create a new event listener container based
     * on an event listener
     * 
     * @param listener
     */
    public CallListenerContainer(CallListener listener) {
        this.listener = listener;
    }
    
    /**
     * Enable auto-remove for this listener
     * 
     * @param enable
     */
    public void enableAutoRemove(boolean enable) {
        this.autoRemove = enable;
    }
    
    
    /**
     * Check, whether auto-remove has been enabled.
     * 
     * @return
     */
    public boolean isAutoRemoveEnabled() {
        return this.autoRemove;
    }
    
    
    /**
     * Get the call listener
     * 
     * @return
     */
    public CallListener getListener() {
        return this.listener;
    }
    
}
