package dev.yue.api.render;

public abstract class Component {
    public Gui gui;

    public Component parent;
    
    public String id;

    public int x, y;

    public int width, height;

    public boolean listenable, draggable, clickable;

    public abstract void render (Canvas matrices, int mouseX, int mouseY, int delta);

    public abstract void key (int keyCode, int scanCode, int modifiers);
    
    public abstract boolean click (int mouseX, int mouseY, int button);

    public abstract Element drag (int mouseX, int mouseY, int deltaX, int deltaY, int button);
}
