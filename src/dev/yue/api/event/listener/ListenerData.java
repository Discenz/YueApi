package dev.yue.api.event.listener;

import java.lang.reflect.Method;

public class ListenerData {
    private Method method;

    private EventListener owner;

    private int priority;

    private boolean active;

    public ListenerData (Method method, EventListener owner, int priority){
        this.method = method;
        this.owner = owner;
        this.priority = priority;
        this.active = true;
    }

    public Method getMethod() {
        return method;
    }

    public EventListener getOwner() {
        return owner;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
