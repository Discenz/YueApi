package dev.yue.api.render;

import java.util.ArrayList;

public class Panel {
    
    public Gui gui;

    public int x, y;

    public int width, height;

    public ArrayList<Element> elements;

    public Panel (Gui gui) {
        this.gui = gui;
    }

    public void render (Canvas matrices, int mouseX, int mouseY, int delta) {
        for (Element element: elements) {
            element.render(matrices, mouseX, mouseY, delta);
        }
    }
    
    public void key (int keyCode, int scanCode, int modifiers) {
        for (Element element: elements) {
            if (!element.listenable) continue;
            element.key(keyCode, scanCode, modifiers);
        }
    }
    
    public boolean click (int mouseX, int mouseY, int button) {
        if (x < mouseX || x >= mouseX + height || y < mouseY || y >= mouseY + height) return false;

        for (Element element: elements) {
            if (!element.clickable) continue;
            if (element.click(mouseX, mouseY, button)) return true;
        }

        return false;
    }

    public Element drag (int mouseX, int mouseY, int deltaX, int deltaY, int button) {        
        if (gui.dragElement != null) return null;

        for (Element element: elements) {
            if (!element.draggable) continue;
            if (element.drag(mouseX, mouseY, deltaX, deltaY, button)) return element;
        }

        return null;
    }
}
