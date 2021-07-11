package dev.yue.api.setting.settings;

import dev.yue.api.setting.Setting;

public class ModeSetting extends Setting<Integer> {
    
    private String[] modes;

    public ModeSetting (String name, String description, int value, String... modes) {
        super(name, description, value);
        this.modes = modes;
    }

    public String getMode () {
        return modes[value];
    }

    public String[] getModes () {
        return modes;
    }

    public void setMode (String name) {
        for (int i = 0; i < modes.length; i++) {
            if (modes[i].equals(name)) {
                value = i;
                return;
            }
        }
    }

}
