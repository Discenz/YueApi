package dev.yue.api.render;

import java.util.ArrayList;

public class Gui {
    
    public ArrayList<Panel> panels;

    public Element dragElement;

    public boolean listenable, clickable, draggable;

    public Gui () {
        this.panels = new ArrayList<Panel>();

        this.listenable = false;
        this.clickable = false;
        this.draggable = false;

        this.dragElement = null;
    }

    public void render (Canvas canvas, int mouseX, int mouseY, int delta) {
        for (Panel panel: panels) {
            panel.render(canvas, mouseX, mouseY, delta);
        }
    }
    
    public void key (int keyCode, int scanCode, int modifiers) {
        if (!this.listenable) return;

        for (Panel panel: panels) {
            panel.key(keyCode, scanCode, modifiers);
        }
    }
    
    public void click (int mouseX, int mouseY, int button) {
        if (!this.clickable) return;

        for (Panel panel: panels) {
            if (panel.click(mouseX, mouseY, button)) return;
        }

    }

    public void drag (int mouseX, int mouseY, int deltaX, int deltaY, int button) {
        if (!this.draggable) return;

        if (this.dragElement != null) {
            this.dragElement.drag(mouseX, mouseY, deltaX, deltaY, button);
            return;
        }

        for (int i = 0; i < panels.size(); i++) {
            Panel panel = panels.get(i);
            this.dragElement = panel.drag(mouseX, mouseY, deltaX, deltaY, button);
            if (this.dragElement != null) return;
        }
    }

}
