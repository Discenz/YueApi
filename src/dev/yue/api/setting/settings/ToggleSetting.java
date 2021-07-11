package dev.yue.api.setting.settings;

import dev.yue.api.setting.Setting;

public class ToggleSetting extends Setting<Boolean> {
    
    public ToggleSetting (String name, String description, boolean value) {
        super(name, description, value);
    }

    public void toggle () {
        this.value = !this.value;
    }

}
