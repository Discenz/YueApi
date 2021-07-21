package dev.yue.api.render;

import java.util.ArrayList;

public class Panel extends Component {

    public ArrayList<Component> components;

    public Panel (Gui gui) {
        this.gui = gui;
    }

    public void render (Canvas matrices, int mouseX, int mouseY, int delta) {
        for (Component component: components) {
            component.render(matrices, mouseX, mouseY, delta);
        }
    }
    
    public void key (int keyCode, int scanCode, int modifiers) {
        for (Component component: components) {
            if (!component.listenable) continue;
            component.key(keyCode, scanCode, modifiers);
        }
    }
    
    public boolean click (int mouseX, int mouseY, int button) {
        if (x < mouseX || x >= mouseX + height || y < mouseY || y >= mouseY + height) return false;

        for (Component component: components) {
            if (!component.clickable) continue;
            if (component.click(mouseX, mouseY, button)) return true;
        }

        return false;
    }

    public Element drag (int mouseX, int mouseY, int deltaX, int deltaY, int button) {        
        if (gui.dragElement != null) return null;

        for (Component component: components) {
            if (!component.draggable) continue;

            Element element = component.drag(mouseX, mouseY, deltaX, deltaY, button);

            if (element != null) return element;
        }

        return null;
    }
}
