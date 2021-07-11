package dev.yue.api.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager<T> {
    
    protected ArrayList<T> list;

    protected String id;

    public Manager (String id) {
        this.list = new ArrayList<>();
        this.id = id;
    }

    public String getID () {
        return this.id;
    }

    @SuppressWarnings("unchecked")
    public void add (T... items) {
        if (items.length == 0) return;

        List<T> list = Arrays.asList(items);

        this.list.addAll(list);
    }

    @SuppressWarnings("unchecked")
    public void remove (T... items) {
        if (items.length == 0) return;

        List<T> list = Arrays.asList(items);

        this.list.removeAll(list);
    }

}
