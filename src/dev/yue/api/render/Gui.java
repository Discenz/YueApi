package dev.yue.api.render;

import java.util.ArrayList;

public class Gui {
    
    public ArrayList<Component> components;

    public Element dragElement;

    public boolean listenable, clickable, draggable;

    public Gui () {
        this.components = new ArrayList<Component>();

        this.listenable = true;
        this.clickable = true;
        this.draggable = true;

        this.dragElement = null;
    }

    public void render (Canvas canvas, int mouseX, int mouseY, int delta) {
        for (Component component: components) {
            component.render(canvas, mouseX, mouseY, delta);
        }
    }
    
    public void key (int keyCode, int scanCode, int modifiers) {
        if (!this.listenable) return;

        for (Component component: components) {
            component.key(keyCode, scanCode, modifiers);
        }
    }
    
    public void click (int mouseX, int mouseY, int button) {
        if (!this.clickable) return;

        for (Component component: components) {
            if (component.click(mouseX, mouseY, button)) return;
        }
    }

    public void drag (int mouseX, int mouseY, int deltaX, int deltaY, int button) {
        if (!this.draggable) return;

        if (this.dragElement != null) {
            this.dragElement.drag(mouseX, mouseY, deltaX, deltaY, button);
            return;
        }

        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            this.dragElement = component.drag(mouseX, mouseY, deltaX, deltaY, button);
            if (this.dragElement != null) return;
        }
    }

}
