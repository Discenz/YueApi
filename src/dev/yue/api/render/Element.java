package dev.yue.api.render;

public class Element {

    public Panel panel;

    public Object value;

    public int x, y;

    public int width, height;

    public boolean clickable, draggable, listenable;

    public Element  (Panel panel, Object value) {
        this.panel = panel;
        this.value = value;
    }
    
    public void render (Canvas canvas, int mouseX, int mouseY, int delta) {
        
    }
    
    public void key (int keyCode, int scanCode, int modifiers) {
        
    }
    
    public boolean click (int mouseX, int mouseY, int button) {
        return false;
    }

    public boolean drag (int mouseX, int mouseY, int deltaX, int deltaY, int button) {
        return false;
    }
}
