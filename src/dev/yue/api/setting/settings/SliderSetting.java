package dev.yue.api.setting.settings;

import dev.yue.api.setting.Setting;

public class SliderSetting<T extends Number> extends Setting<T> {
 
    protected T min, max;

    public SliderSetting (String name, String description, T value, T min, T max) {
        super(name, description, value);
        this.min = min;
        this.max = max;
    }

    public T getMin () {
        return this.min;
    }

    public T getMax () {
        return this.max;
    }
    
    @Override
    public void setValue (T value) {
        if (value.doubleValue() > max.doubleValue() || value.doubleValue() < min.doubleValue()) return;
        super.setValue(value);
    }
    
}
