package dev.yue.api.feature.command;

import dev.yue.api.feature.Feature;

public class Command extends Feature {
    
    protected String usage;

    public Command (String name, String description, String usage) {
        super(name, description);
    }

    public String getUsage () {
        return this.usage;
    }

    public void run (String[] args) {
        
    }

}
