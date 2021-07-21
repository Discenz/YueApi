package dev.yue.api.feature.module;

import java.util.ArrayList;
import java.util.Arrays;

import dev.yue.api.feature.Feature;
import dev.yue.api.setting.Setting;

public class Module extends Feature {
    
    protected boolean toggled, hidden;

    protected ArrayList<Setting<?>> settings;

    protected Class<? extends Category> category;

    protected int key;

    public Module (String name, Class<? extends Category> category, String description, Setting<?>... settings) {
        super(name, description);
        this.category = category;
        this.settings = new ArrayList<Setting<?>>(Arrays.asList(settings));
        this.toggled = false;
        this.hidden = false;
        this.key = 0;

        for (Setting<?> setting: settings) {
            setting.setParent(this);
        }
    }

    public void onEnable () {

    }

    public void onDisable () {

    }

    public void toggle () {
        this.toggled = !this.toggled;
        if (this.toggled) onEnable();
        else onDisable();
    }

    public void setToggled (boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) onEnable();
        else onDisable();
    }

    public void setHidden (boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public ArrayList<Setting<?>> getSettings () {
        return this.settings;
    }

    public Setting<?> getSetting (int i) {
        if (i < 0 || i >= settings.size()) return null;
        return settings.get(i);
    }

    public Setting<?> getSetting (String name) {
        for (Setting<?> setting: settings) {
            if (setting.getName().equalsIgnoreCase(name)) return setting;
        }
        return null;
    }

}
