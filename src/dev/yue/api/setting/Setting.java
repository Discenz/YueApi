package dev.yue.api.setting;

import dev.yue.api.feature.Feature;

public class Setting<T> extends Feature {

    protected Feature parent;

    protected T value;
    
    public Setting (String name, String description, T value) {
        super(name, description);
        this.parent = null;
        this.value = value;
    }

    public T getValue () {
        return this.value;
    }

    public void setValue (T value) {
        this.value = value;
    }

    public Feature getParent () {
        return this.parent;
    }

    public void setParent (Feature parent) {
        this.parent = parent;
    }

}
