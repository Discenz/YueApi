package dev.yue.api.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dev.yue.api.event.events.Event;
import dev.yue.api.event.exception.EventSystemException;
import dev.yue.api.event.listener.EventHandler;
import dev.yue.api.event.listener.EventListener;
import dev.yue.api.event.listener.ListenerData;

public class EventBus {

    //Event Map (Contains objects and methods)
    private HashMap<Class<?>, List<ListenerData>> eventMap;
    //Listener Map (Faster unsubscription)
    private HashMap<EventListener, List<Class<?>>> subscriptionMap;

    public EventBus () {
        eventMap = new HashMap<>();
        subscriptionMap = new HashMap<>();
    }

    public void subscribe (EventListener listener) {
        //Check if method is previously subscribed, if not add to cache
        boolean subscriptionCached = subscriptionMap.containsKey(listener);
        if(!subscriptionCached) {
            subscriptionMap.put(listener, new ArrayList<>());
        }

        //Fetch list of subscribed data
        List<Class<?>> subscriptionList = subscriptionMap.get(listener);

        //Return empty EventListeners
        if(subscriptionCached && subscriptionList.isEmpty()) return;

        //Reflection calls: get EventListener's class and methods
        Class<?> listenerClass = listener.getClass();

        Method[] methods = listenerClass.getMethods();

        for(Method method: methods){

            //Skip methods which are not listeners
            if(!method.isAnnotationPresent(EventHandler.class)) continue;

            //Method must have 1 parameter
            if(method.getParameterCount() != 1) throw new EventSystemException("Method " + method.getName() + " has invalid parameter count");

            Class<?> eventClass = method.getParameterTypes()[0];

            //Parameter must be subclass of event
            if(!Event.class.isAssignableFrom(eventClass)) throw new EventSystemException("Method " + method.getName() + " has invalid parameter type");

            //If previously subscribed, activate data. If currently subscribed, cancel.
            if(subscriptionCached) {
                for(ListenerData data: eventMap.get(eventClass)){
                    if(data.getMethod().equals(method)) {
                        //Stop subscribing - object is already subscribed
                        if(data.isActive()) return;
                        //Subscribe method and stop searching
                        data.setActive(true);
                        break;
                    }
                }
            }

            //If not previously subscribed, create new data.
            else {
                //Creates list for event type in listener map if there is none
                if(!eventMap.containsKey(eventClass)) eventMap.put(eventClass, new ArrayList<ListenerData>());

                //Gets event priority from annotation
                int priority = method.getAnnotation(EventHandler.class).priority();

                //Stores listening method, parent object, and event priority in ListenerData object so reflection is not used when posting event
                ListenerData data = new ListenerData(method, listener, priority);

                List<ListenerData> list = eventMap.get(eventClass);

                list.add(data);

                //Swap it into correct position via event priority
                int i = list.size() -1;
                while (i>0 && priority > list.get(i-1).getPriority()) {
                    Collections.swap(list, i, i-1);
                    i--;
                }

                //Adds to list of subscribed events if it has not been added yet
                if(!subscriptionList.contains(eventClass)) subscriptionList.add(eventClass);
            }

        }
    }

    public void unsubscribe (EventListener listener){
        boolean subscriptionCached = subscriptionMap.containsKey(listener);

        //If listener has not been subscribed before return
        if(!subscriptionCached) return;

        List<Class<?>> subscriptionList = subscriptionMap.get(listener);

        //Return if there is nothing to unsubscribe from
        if(subscriptionList.isEmpty()) return;

        //Temporary copy of subscribed list with removable elements
        List<Class<?>> tempList = new ArrayList<>(subscriptionList);

        Method[] methods = listener.getClass().getMethods();

        for(Method method: methods){

            //Skip methods which are not listeners
            if(!method.isAnnotationPresent(EventHandler.class)) continue;

            //Event listener methods can only have 1 parameter, the event
            if(method.getParameterCount() != 1) throw new EventSystemException("Method " + method.getName() + " has invalid parameter count");

            Class<?> eventClass = method.getParameterTypes()[0];

            //Parameter must be subclass of event
            if(!Event.class.isAssignableFrom(eventClass)) throw new EventSystemException("Method " + method.getName() + " has invalid parameter type");

            //Do not search if class does not subscribe to the event
            if(tempList.contains(eventClass)){
                //All other methods using that event are automatically handled in this cycle
                tempList.remove(eventClass);

                List<ListenerData> list = eventMap.get(eventClass);

                for(ListenerData data: list){
                    if(data.getOwner().equals(listener)) {
                        data.setActive(false);
                    }
                }
            }

        }
    }

    public void post (Event event) {
        Class<?> eventClass = event.getClass();

        //If no listeners are subscribed to the event
        if(!eventMap.containsKey(eventClass)) return;

        //Fetch all
        List<ListenerData> list = eventMap.get(eventClass);

        for (ListenerData data: list) {

            //If listener is not currently subscribed, skip
            if(!data.isActive()) continue;

            try {
                data.getMethod().invoke(data.getOwner(), event);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
