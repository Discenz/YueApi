package dev.yue.api.feature;

public class Feature {
    
    protected String name, description;

    public Feature (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }

}
